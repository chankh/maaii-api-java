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

import com.m800.api.request.sms.SmsMessageRequest;
import com.m800.api.response.sms.SmsMessageResponse;

/**
 * This interface defines the basic operations available through the M800 REST API service.
 *
 * @author KH Chan
 * @since 1.0
 */
public interface M800SmsApi {

  /**
   * Sends a text SMS message.
   *
   * @param from         sender of the SMS, in E164 format, should not contain any "00" or "+"
   *                     prefix.
   * @param to           recipient of the SMS, in E164 format, should not contain any "00" or "+"
   *                     prefix.
   * @param messageClass message class of the SMS, can be either "normal" or "flash".
   * @param body         SMS contents in UTF-8 text.
   * @return response from server.
   */
  SmsMessageResponse sendTextSms(String from, String to, String messageClass, String body);

  /**
   * Sends a binary SMS message.
   *
   * @param from         sender of the SMS, in E164 format, should not contain any "00" or "+"
   *                     prefix.
   * @param to           recipient of the SMS, in E164 format, should not contain any "00" or "+"
   *                     prefix.
   * @param messageClass message class of the SMS, can be either "normal" or "flash".
   * @param body         SMS binary contents in Base64 format.
   * @return response from server.
   */
  SmsMessageResponse sendBinarySms(String from, String to, String messageClass, String body);

  /**
   * Sends a SMS message.
   *
   * @param to      recipient of the SMS, in E164 format, should not contain any "00" or "+"
   *                prefix.
   * @param request the raw request of SMS message to be sent.
   * @return response from server.
   */
  SmsMessageResponse sendSms(String to, SmsMessageRequest request);
}
