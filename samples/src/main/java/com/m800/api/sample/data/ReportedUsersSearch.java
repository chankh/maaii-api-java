package com.m800.api.sample.data;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.m800.api.M800Config;
import com.m800.api.M800DataSearchApi;
import com.m800.api.jersey.data.search.M800ReportedUsersSearchApiClient;
import com.m800.api.response.data.Page;
import com.m800.api.response.data.dto.UserReportEventRecord;

/**
 * Get reported users list
 * Usage: ReportedUsersSearch --help
 */
public class ReportedUsersSearch {

  public static void main(String[] args) throws URISyntaxException {

    Options options = new Options();

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

    options.addOption(Option.builder("from")
        .desc("query param: search result start from timestamp")
        .hasArg()
        .required()
        .build());

    options.addOption(Option.builder("to")
        .desc("query param: search result end of timestamp")
        .hasArg()
        .required()
        .build());

    options.addOption(Option.builder("page")
        .desc("query param: page number which start from 0")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("size")
        .desc("query param: number of items that included per page. default 100")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("username")
        .desc("filter: username who reported an user")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("carrier")
        .desc("filter: user's carrier who reported an user")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("country")
        .desc("filter: user's country")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("ipCountry")
        .desc("filter: user's country which resolved by the user's source IP")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("reasonMessage")
        .desc("filter: reason for reporting the user")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("isp")
        .desc("filter: user's ISP which resolved by the user's source IP")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("platform")
        .desc("filter: user's active device platform")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("application")
        .desc("filter: application identifier for the APP in user's active device")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("source")
        .desc("filter: page in the APP where reporting the user")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("targetCarrier")
        .desc("filter: carrier of the user who reported")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("targetUsername")
        .desc("filter: username of the user who reported")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("targetCountry")
        .desc("filter: country of the user who reported")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("targetPlatform")
        .desc("filter: platform of the user's active device who reported")
        .hasArg()
        .required(false)
        .build());

    options.addOption(Option.builder("targetApplication")
        .desc("filter: application identifier of the user's active device who reported")
        .hasArg()
        .required(false)
        .build());

    CommandLineParser parser = new DefaultParser();

    try {
      CommandLine cmd = parser.parse(options, args, true);
      String key = cmd.getOptionValue("key");
      String secret = cmd.getOptionValue("secret");
      String apiUri = cmd.getOptionValue("uri");

      Map<String, Object> queryParam = generateQueryParam(cmd);

      sendRequest(key, secret, apiUri, queryParam);
    } catch (ParseException e) {
      System.out.println("Invalid options: " + e.getMessage());
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("ReportedUsersSearch [OPTIONS]...", options);
      System.exit(1);
    } catch (JsonProcessingException e) {
      System.out.println("Fail to parse the response. " + e.getMessage());
      System.exit(1);
    }
  }

  /**
   * Method creating the request and invoking Reported Users Search API on M800 service
   *
   * @param key         Developer key
   * @param secret      Developer Key
   * @param apiUri      M800 service base uri
   * @param queryParam  Query Parameters for the API
   */
  private static void sendRequest(String key, String secret, String apiUri,
                                 Map<String, Object> queryParam) throws JsonProcessingException {
    M800Config config = new M800Config(key, secret, URI.create(apiUri));
    M800DataSearchApi client = new M800ReportedUsersSearchApiClient(config);
    Page<UserReportEventRecord> response = client.send(queryParam);

    System.out.println(String.format("response = %s",
        new ObjectMapper().writeValueAsString(response)));
  }

  private static void putOptionalQueryParam(CommandLine cmd, Map<String, Object> queryParam,
                                            String fieldName, String paramName, Class<?> paramClass) {
    if (cmd.hasOption(fieldName)) {
      if (Objects.equals(paramClass, String.class)) {
        queryParam.put(paramName, cmd.getOptionValue(fieldName));
      } else if (Objects.equals(paramClass, Integer.class)) {
        queryParam.put(paramName, Integer.valueOf(cmd.getOptionValue(fieldName)));
      }
    }
  }

  private static Map<String, Object> generateQueryParam(CommandLine cmd) {
    Map<String, Object> queryParam = Maps.newHashMap();
    // required
    String from = cmd.getOptionValue("from");
    String to = cmd.getOptionValue("to");

    // optional
    queryParam.put("from", Long.valueOf(from));
    queryParam.put("to", Long.valueOf(to));

    putOptionalQueryParam(cmd, queryParam, "page", "page", Integer.class);
    putOptionalQueryParam(cmd, queryParam, "size", "size", Integer.class);
    putOptionalQueryParam(cmd, queryParam, "username", "username", String.class);
    putOptionalQueryParam(cmd, queryParam, "carrier", "carrier", String.class);
    putOptionalQueryParam(cmd, queryParam, "country", "country", String.class);
    putOptionalQueryParam(cmd, queryParam, "ipCountry", "ip_country", String.class);
    putOptionalQueryParam(cmd, queryParam, "reasonMessage", "reason_message", String.class);
    putOptionalQueryParam(cmd, queryParam, "isp", "isp", String.class);
    putOptionalQueryParam(cmd, queryParam, "platform", "platform", String.class);
    putOptionalQueryParam(cmd, queryParam, "application", "application", String.class);
    putOptionalQueryParam(cmd, queryParam, "source", "source", String.class);
    putOptionalQueryParam(cmd, queryParam, "targetCarrier", "target_carrier", String.class);
    putOptionalQueryParam(cmd, queryParam, "targetUsername", "target_username", String.class);
    putOptionalQueryParam(cmd, queryParam, "targetCountry", "target_country", String.class);
    putOptionalQueryParam(cmd, queryParam, "targetPlatform", "target_platform", String.class);
    putOptionalQueryParam(cmd, queryParam, "targetApplication", "target_application", String.class);

    return queryParam;
  }

}
