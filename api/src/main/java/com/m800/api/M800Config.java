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
package com.m800.api;

import java.net.URI;

/**
 * Holds configuration parameters for a M800Api instance.
 *
 * @author KH Chan
 * @since 1.0
 */
public class M800Config {

  private final String developerKey;
  private final String developerSecret;
  private final URI apiUri;

  /**
   * Creates a new instance with the specified developer key, secret and API URI endpoint.
   *
   * @param developerKey    the developer key to use for communication with M800 API Server.
   * @param developerSecret the developer secret to sign API requests.
   * @param apiUri          the URI used for communication to M800 API Server.
   */
  public M800Config(String developerKey, String developerSecret, URI apiUri) {
    this.developerKey = developerKey;
    this.developerSecret = developerSecret;
    this.apiUri = apiUri;
  }

  /**
   * Returns the developer key to use for communication with M800 API Server.
   *
   * @return the developer key to use for communication with M800 API Server.
   */
  public String getDeveloperKey() {
    return developerKey;
  }

  /**
   * Returns the developer secret to sign API requests.
   *
   * @return the developer secret to sign API requests.
   */
  public String getDeveloperSecret() {
    return developerSecret;
  }

  /**
   * Returns the URI used for communication to M800 API Server.
   *
   * @return the URI used for communication to M800 API Server.
   */
  public URI getApiUri() {
    return apiUri;
  }

}
