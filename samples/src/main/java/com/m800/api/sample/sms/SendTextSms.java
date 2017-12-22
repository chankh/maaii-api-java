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
package com.m800.api.sample.sms;

import com.m800.api.M800SmsApi;
import com.m800.api.M800Config;
import com.m800.api.jersey.M800ApiClient;
import com.m800.api.jersey.M800SmsApiClient;
import com.m800.api.response.sms.SmsMessageResponse;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Sends a text SMS.
 * Usage: SendTextSms --help
 */
public final class SendTextSms {

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

    CommandLineParser parser = new DefaultParser();

    try {
      CommandLine cmd = parser.parse(options, args, true);

      String key = cmd.getOptionValue("key");
      String secret = cmd.getOptionValue("secret");
      String apiUri = cmd.getOptionValue("uri");
      String from = cmd.getOptionValue("from");
      String to = cmd.getOptionValue("to");
      String msg = cmd.getArgs()[0];

      sendTextSms(key, secret, apiUri, from, to, msg);
    } catch (ParseException e) {
      System.out.println("Invalid options: " + e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("SendTextSms [OPTIONS]... MESSAGE", options);
      System.exit(1);
    }
  }

  public static void sendTextSms(String key, String secret, String apiUri, String from, String to, String message)
      throws URISyntaxException {
    M800SmsApi api = new M800SmsApiClient(new M800Config(key, secret, new URI(apiUri)));
    SmsMessageResponse response = api.sendTextSms(from, to, "normal", message);

    System.out.println("success = " + String.valueOf(response.isSuccess()));
    System.out.println("totalCost = " + String.valueOf(response.getTotalCost()));
    System.out.println("segmentCount = " + String.valueOf(response.getSegmentCount()));

  }
}
