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

import com.google.common.collect.Maps;
import com.google.common.io.Closeables;

import com.m800.api.util.Bytes;
import com.m800.api.util.RequestSigner;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class ClientRequestSigningFilterTester {

  private final String key = "a-testing-key";
  private final String secret = "a-testing-secret";

  private static final byte[] entity = "I am an Entity Body!!!! Shock@!".getBytes();

  private ServerSocket server;

  @BeforeClass()
  public void before() throws Exception {
    server = new ServerSocket();
    server.bind(new InetSocketAddress(0));
  }

  @AfterClass(alwaysRun = true)
  public void after() throws IOException {
    if (server != null) {
      Closeables.close(server, true);
    }
  }

  @Test(groups = "integration")
  public void canSignRequest() throws IOException, URISyntaxException {

    Client client = JerseyClientBuilder.newClient();
    try {
      client.register(new ClientRequestSigningFilter(key, secret));

      WebTarget target = client.target(new URI("http", null, server.getInetAddress().getHostAddress(), server.getLocalPort(), "", null, null));

      target.request().async().post(Entity.text(entity));

      try (Socket socket = server.accept()) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        String md5 = null;
        String authentication = null;
        String dateString = null;
        while ((line = reader.readLine()) != null) {
          if (line.equals("")) {
            break;
          }
          if (line.startsWith("Content-MD5:")) {
            md5 = line.substring("Content-MD5: ".length());
          } else if (line.startsWith("Authorization:")) {
            authentication = line.substring("Authorization: ".length());
          } else if (line.startsWith("Date:") && dateString == null) {
            dateString = line.substring("Date: ".length());
          } else if (line.startsWith("X-M-Date:")) {
            dateString = line.substring("X-M-Date: ".length());
          }
        }

        RequestSigner signer = new RequestSigner();
        final String md5String = Bytes.md5String(entity);

        Assert.assertNotNull(md5, "md5 in request can not be null!");
        Assert.assertEquals(md5, md5String, "md5 String is not valid!");

        Assert.assertNotNull(authentication, "authentication String is null!");

        // Parse the whole authorization
        StringTokenizer st = new StringTokenizer(authentication, " ,", true);
        Map<String, String> authParams = Maps.newHashMap();
        while (st.hasMoreTokens()) {
          String[] tokens = st.nextToken().split("=");
          switch (tokens.length) {
            case 1:
              authParams.put("SCHEME", tokens[0]);
              break;
            case 2:
              authParams.put(tokens[0], tokens[1].substring(1, tokens[1].length() - 1));
              break;
            default:
              Assert.fail();
          }
        }
        final String sign = signer.sign(secret, "POST", "/", md5String, "text/plain", dateString,
                                        authParams.get("nonce"));

        Assert.assertEquals(sign, authParams.get("signature"),
                            "the signature of the request is not valid!");
      }
    } finally {
      client.close();
    }
  }

}
