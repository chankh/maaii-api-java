package com.m800.api.jersey.data.search;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.m800.api.M800Config;
import com.m800.api.M800DataSearchApi;
import com.m800.api.jersey.M800ApiClient;

/**
 * @author Angela Cheung
 * @since 23 Jun 2016 12:43 PM
 */
public abstract class M800DataSearchApiClient extends M800ApiClient implements M800DataSearchApi {

  private URI apiUri;

  public M800DataSearchApiClient(M800Config config) {
    super(config);
    apiUri = UriBuilder.fromUri(super.getApiUri()).path(API_V1).path("/data/search")
        .build();
  }

  @Override
  protected URI getApiUri() {
    return apiUri;
  }
}
