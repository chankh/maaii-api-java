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
package com.m800.api.exception;

import java.io.IOException;

/**
 * Represents an error response from M800 API server.
 *
 * @author KH Chan
 * @since 1.0
 */
public class M800ApiException extends IOException {

  private int status;
  private int code;

  public M800ApiException(int status, int code, String message) {
    super(message);
    this.status = status;
    this.code = code;
  }

  /**
   * Returns the HTTP status code of this error.
   *
   * @return the HTTP status code of this error.
   */
  public int getStatus() {
    return status;
  }

  /**
   * Returns the Error code of this error.
   *
   * @return the Error code of this error.
   */
  public int getCode() {
    return code;
  }
}
