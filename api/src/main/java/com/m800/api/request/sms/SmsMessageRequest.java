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
package com.m800.api.request.sms;

/**
 * Represents a SMS send message request.
 *
 * @author KH Chan
 * @since 1.0
 */
public class SmsMessageRequest {

  private String from;
  private String type;
  private String messageClass;
  private String body;

  /**
   * Returns the sender of SMS message, in E164 format, should not contain "00" or "+" prefix.
   *
   * @return the sender of SMS message, in E164 format, should not contain "00" or "+" prefix.
   */
  public String getFrom() {
    return from;
  }

  /**
   * Sets the sender of SMS message, in E164 format, should not contain "00" or "+" prefix.
   *
   * @param from the sender of SMS message, in E164 format, should not contain "00" or "+" prefix.
   */
  public void setFrom(String from) {
    this.from = from;
  }

  /**
   * Returns the type of SMS message, should be either "text" or "binary".
   *
   * @return the type of SMS message, should be either "text" or "binary".
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of SMS message, should be either "text" or "binary".
   *
   * @param type the type of SMS message, should be either "text" or "binary".
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns the message class of SMS message, should be either "normal" or "flash".
   *
   * @return the message class of SMS message, should be either "normal" or "flash".
   */
  public String getMessageClass() {
    return messageClass;
  }

  /**
   * Sets the message class of SMS message, should be either "normal" or "flash".
   *
   * @param messageClass the message class of SMS message, should be either "normal" or "flash".
   */
  public void setMessageClass(String messageClass) {
    this.messageClass = messageClass;
  }

  /**
   * Returns the SMS message body contents. For text messages, this should be a UTF-8 string. For
   * binary messages, this should be Base64 encoded.
   *
   * @return the SMS message body contents.
   */
  public String getBody() {
    return body;
  }

  /**
   * Sets the SMS message body contents. For text messages, this should be a UTF-8 string. For
   * binary messages, this should be Base64 encoded.
   *
   * @param body the SMS message body contents.
   */
  public void setBody(String body) {
    this.body = body;
  }
}
