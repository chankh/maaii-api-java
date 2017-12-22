package com.m800.api.jersey.data.search;

import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


import com.google.common.collect.Maps;
import com.m800.api.M800Config;
import com.m800.api.response.data.Page;
import com.m800.api.response.data.dto.UserReportEventRecord;

/**
 * @author Angela Cheung
 * @since 23 Jun 2016 12:31 PM
 */
public class M800ReportedUsersSearchApiClient extends M800DataSearchApiClient {

  public M800ReportedUsersSearchApiClient(M800Config config) {
    super(config);
  }

  @Override
  public Page<UserReportEventRecord> send(Map queryParam) {
    WebTarget target = getClient()
        .target(UriBuilder.fromUri(getApiUri()).path("/reported_users/query"));
    if (nonNull(queryParam) || !queryParam.isEmpty()) {
      Map<String, Object> newQueryParam = Maps.newHashMap(queryParam);
      for(Map.Entry<String, Object> entry : newQueryParam.entrySet()) {
        target = target.queryParam(entry.getKey(), entry.getValue());
      }
    }
    return target.request(MediaType.APPLICATION_JSON_TYPE)
        .get(new GenericType<Page<UserReportEventRecord>>(){});

  }
}
