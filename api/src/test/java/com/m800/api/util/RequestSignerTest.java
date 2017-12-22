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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author KH Chan
 */
public class RequestSignerTest {

  @Test
  public void testSign() {
    String secret = "a-demo-secret";
    String method = "POST";
    String requestUri = "/api/1.0/debug/echo";
    String contentMd5 = "2aa2f9b90fea8843a78e434dff26f6b6";
    String contentType = "application/json";
    String date = "Thu, 12 Sep 2013 18:49:58 GMT";
    String nonce = "123456789";
    String expected = "d11ede54e2ffa83d58a42d90c3a39132f928a46b729c4b0621ad1c854dff618f";

    String actual = new RequestSigner().sign(secret, method, requestUri, contentMd5, contentType,
                                             date, nonce);

    assertEquals(actual, expected);
  }

}
