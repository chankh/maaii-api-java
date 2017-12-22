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

import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Abstract implementation for M800 API clients. This class provides common configuration that can
 * be used conveniently by all M800 APIs clients.
 *
 * @author KH Chan
 * @since 1.1
 */
public abstract class M800ApiClient  {

  protected static final String API_V1 = "/api/1.0";
  private final URI apiUri;
  private final Client client;

  public M800ApiClient(M800Config config) {
    this.apiUri = config.getApiUri();
    this.client = ClientBuilder.newClient()
                               .register(ObjectMapperProvider.class)
                               .register(new ClientRequestSigningFilter(config))
                               .register(new GrizzlyConnectorProvider())
                               .register(new JacksonFeature())
                               .register(new ErrorFilter());
  }

  protected URI getApiUri() {
    return apiUri;
  }

  protected Client getClient() {
    return client;
  }
}
