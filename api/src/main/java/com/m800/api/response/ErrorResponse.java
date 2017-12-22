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
package com.m800.api.response;

/**
 * Represents an error response from M800 API server.
 *
 * @author KH Chan
 * @since 1.0
 */
public class ErrorResponse {

  private M800ApiError error;

  public class M800ApiError {

    private int status;
    private int code;
    private String message;

    public M800ApiError() {

    }

    public M800ApiError(int status, int code, String message) {
      this.code = code;
      this.message = message;
    }

    /**
     * Returns the HTTP status code.
     *
     * @return the HTTP status code.
     */
    public int getStatus() {
      return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status the HTTP status code.
     */
    public void setStatus(int status) {
      this.status = status;
    }

    /**
     * Returns the error code.
     *
     * @return the error code.
     */
    public int getCode() {
      return code;
    }

    /**
     * Returns the error message.
     *
     * @return the error message.
     */
    public String getMessage() {
      return message;
    }
  }

  public M800ApiError getError() {
    return error;
  }

  public void setError(M800ApiError error) {
    this.error = error;
  }
}
