/*
 * This sample code is licensed under the Apache license, with the following
 * additional copyrights and restrictions:
 *
 * Copyright 2014 M800
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.m800.api.jersey;

import com.m800.api.M800Config;
import com.m800.api.util.Bytes;
import com.m800.api.util.RequestSigner;
import com.m800.api.util.UriUtils;

import org.glassfish.jersey.message.internal.HttpDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * @author Nicolas Berthet
 * @since 1.0
 */
public class ClientRequestSigningFilter implements ClientRequestFilter {

  private static final String AUTHORIZATION_SCHEME = "MAAIISDK10";

  private static final String CONTENT_MD5_HEADER = "Content-MD5";

  private static final String EXPLICIT_DATE = "X-M-Date";

  private Logger log = LoggerFactory.getLogger(getClass());

  private RequestSigner signer = new RequestSigner();

  private String key;
  private String secret;
  private String scheme;

  public ClientRequestSigningFilter(M800Config config) {
    this(config.getDeveloperKey(), config.getDeveloperSecret());
  }

  public ClientRequestSigningFilter(String key, String secret) {
    this(key, secret, AUTHORIZATION_SCHEME);
  }

  public ClientRequestSigningFilter(String key, String secret, String scheme) {
    this.key = key;
    this.secret = secret;
    this.scheme = scheme;
  }

  @Override
  public void filter(final ClientRequestContext request) throws IOException {
    try {
      if (request.hasEntity()) {
        final OutputStream out = request.getEntityStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream() {
          @Override
          public void close() throws IOException {
            byte[] bytes = toByteArray();

            String md5 = Bytes.md5String(bytes);
            request.getHeaders().add(CONTENT_MD5_HEADER, md5);

            signRequest(request, nonce(), md5);

            out.write(bytes);
          }
        };
        request.setEntityStream(baos);
      } else {
        signRequest(request, nonce(), null);
      }
    } catch (Exception e) {
      log.error("Unable to sign the request", e);
      request.abortWith(Response.status(Response.Status.FORBIDDEN).build());
    }
  }

  private void signRequest(ClientRequestContext request, String nonce, String contentMd5) {

    String dateString = HttpDateFormat.getPreferredDateFormat().format(new Date());
    request.getHeaders().putSingle(EXPLICIT_DATE, dateString);

    String
        sign =
        signer.sign(secret, request.getMethod(), UriUtils.removeAuthority(request.getUri()),
                    contentMd5, request.getHeaderString(
            HttpHeaders.CONTENT_TYPE), dateString, nonce);

    request.getHeaders().add(HttpHeaders.AUTHORIZATION, String
        .format("%s key=\"%s\", nonce=\"%s\", signature=\"%s\"", scheme, key, nonce, sign));
  }

  /**
   * Generate a nonce based on a SecureRandom (SHA1PRNG) and the milliseconds timestamp.
   *
   * If SHA1PRNG is not available, we'll fallback to UUID based nonce.
   */
  private String nonce() {
    String nonceString = Long.toHexString(System.currentTimeMillis());
    try {
      byte[] nonce = new byte[8];
      SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
      sr.nextBytes(nonce);
      nonceString += Bytes.toHexString(nonce);
    } catch (NoSuchAlgorithmException e) {
      log.warn("Unable to use SHA1PRNG, fallback to UUIDs");
      nonceString += UUID.randomUUID().toString();
    }

    return nonceString;
  }
}
