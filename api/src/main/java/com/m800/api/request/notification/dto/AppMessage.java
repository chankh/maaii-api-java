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
package com.m800.api.request.notification.dto;


import com.google.common.base.MoreObjects;

/**
 * Application message that send to the client user.
 */
public class AppMessage {
  /**
   * Content of the Application message.
   */
  private String content;

  public String getContent () {
    return content;
  }

  public void setContent ( String content ) {
    this.content = content;
  }

  @Override
  public String toString () {
    return MoreObjects.toStringHelper(this).add ("content", content).toString ();
  }
}