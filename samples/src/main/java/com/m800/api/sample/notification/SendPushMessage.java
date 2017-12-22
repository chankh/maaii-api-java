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
package com.m800.api.sample.notification;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.m800.api.M800Config;
import com.m800.api.M800PushApi;
import com.m800.api.jersey.M800PushApiClient;
import com.m800.api.request.notification.dto.Push;
import com.m800.api.response.ErrorResponse;
import com.m800.api.response.notification.PushNotificationResponse;

/**
 * Sends a push notification.
 * Usage: SendPushMessage --help
 */
public final class SendPushMessage {

  public static void main(String[] args) throws URISyntaxException {

    Options options = new Options();
    options.addOption(Option.builder("from")
                          .desc("sender's phone number")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("to")
                          .desc("recipient's phone number")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("carrier")
                          .desc("carrier ID")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("key")
                          .desc("developer key")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("secret")
                          .desc("developer key secret")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("uri")
                          .desc("HTTP API URI")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("push_message")
                          .desc("push message")
                          .hasArg()
                          .required()
                          .build());

    options.addOption(Option.builder("app_message")
                          .desc("app message")
                          .hasArg()
                          .required()
                          .build());

    CommandLineParser parser = new DefaultParser();

    try {
      CommandLine cmd = parser.parse(options, args, true);
      String key = cmd.getOptionValue("key");
      String secret = cmd.getOptionValue("secret");
      String apiUri = cmd.getOptionValue("uri");
      String from = cmd.getOptionValue("from");
      String to = cmd.getOptionValue("to");
      String carrierId = cmd.getOptionValue("carrier");
      String appMessage = cmd.getOptionValue("app_message");
      String pushMessage = cmd.getOptionValue("push_message");
      sendRequest(key, secret, apiUri, from, to, carrierId, appMessage, pushMessage);
    } catch (ParseException e) {
      System.out.println("Invalid options: " + e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("SendPushMessage [OPTIONS]... MESSAGE", options);
      System.exit(1);
    }
  }

  /**
   * Method creating the request and invoking Push message API on M800 service
   *
   * @param key         Developer key
   * @param secret      Developer Key
   * @param apiUri      M800 service base uri
   * @param from        Sender address (Only receiver id without domain)
   * @param to          Receiver address (Only receiver id without domain)
   * @param carrierId   domain identifier
   * @param appMessage  The message which will be sent to Application over xmpp channel
   * @param pushMessage The message which will be sent to Application over push channel
   *                    (GCM, APNS, WNS) depending platform
   */
  public static void sendRequest(String key, String secret, String apiUri, String from, String to,
                                 String carrierId, String appMessage, String pushMessage) {
    M800Config config = new M800Config(key, secret, URI.create(apiUri));
    M800PushApi client = new M800PushApiClient(config);
    PushNotificationResponse response = client.send(from, to, carrierId, pushMessage, appMessage);

    System.out.println("requestId = " + response.getRequestId());
  }
}
