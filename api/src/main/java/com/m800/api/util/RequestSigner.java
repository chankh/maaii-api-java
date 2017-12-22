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
package com.m800.api.util;

import com.google.common.base.MoreObjects;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Nicolas Berthet
 * @since 1.0
 */
public class RequestSigner {

  private static final char LF = '\n';

  public String sign(String secret, String method, String requestUri,
                     String contentMd5, String contentType, String dateString,
                     String nonce) {


    final String signaturePayload = method + LF + requestUri + LF +
                              MoreObjects.firstNonNull(contentMd5, "") + LF +
                              MoreObjects.firstNonNull(contentType, "") + LF +
                              dateString + LF + nonce;

    return hmacSha256(secret, signaturePayload);
  }

  /**
   * Apply HmacSHA256 algorithm on the payload string using given secret.
   *
   * @param secret  secret used to generate the hash
   * @param payload the payload to hash
   * @return a HEX string representation of the hash
   */
  private String hmacSha256(String secret, String payload) {
    try {
      final String algo = "HmacSHA256";

      final Mac hmac = Mac.getInstance(algo);
      hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), algo));

      return Bytes.toHexString(hmac.doFinal(payload.getBytes("UTF-8")));
    } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
      throw new RuntimeException("Unable to verify signature", e);
    }
  }
}