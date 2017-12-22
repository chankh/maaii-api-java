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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m800.api.exception.M800ApiException;
import com.m800.api.response.ErrorResponse;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

/**
 * @author KH Chan
 * @since 1.0
 */
public class ErrorFilter implements ClientResponseFilter {

  @Override
  public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
      throws IOException {

    final int status = responseContext.getStatus();
    if (status > 299) {
      final InputStream is = responseContext.getEntityStream();
      final ObjectMapper mapper = new ObjectMapper();
      final ErrorResponse response = mapper.readValue(is, ErrorResponse.class);
      throw new M800ApiException(status, response.getError().getCode(), response.getError().getMessage());
    }
  }
}
