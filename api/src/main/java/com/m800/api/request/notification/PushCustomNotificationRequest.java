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
package com.m800.api.request.notification;

import com.google.common.base.MoreObjects;
import com.m800.api.request.notification.dto.AppMessage;
import com.m800.api.request.notification.dto.Push;

/**
 * Notification Detail send by developer to client user.
 */
public class PushCustomNotificationRequest {

  /**
   * Sender of the request
   */
  private String sender;

  /**
   * the push message details that describe the push message.
   */
  private Push push;

  /**
   * the application message (in-app message) details that describe the application message.
   */
  private AppMessage appMessage;

  public String getSender () {
    return sender;
  }

  public void setSender ( String sender ) {
    this.sender = sender;
  }

  public Push getPush () {
    return push;
  }

  public void setPush ( Push push ) {
    this.push = push;
  }

  public AppMessage getAppMessage () {
    return appMessage;
  }

  public void setAppMessage ( AppMessage appMessage ) {
    this.appMessage = appMessage;
  }

  @Override
  public String toString () {
    return MoreObjects.toStringHelper(this)
        .add("sender", sender)
        .add("push", push)
        .add("appMessage", appMessage)
        .toString();
  }
}
