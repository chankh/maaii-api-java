/**
 * @since Jun 29, 2015
 */
package com.m800.api.jersey;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.m800.api.M800Config;
import com.m800.api.M800PushApi;
import com.m800.api.request.notification.PushCustomNotificationRequest;
import com.m800.api.request.notification.dto.AppMessage;
import com.m800.api.request.notification.dto.Push;
import com.m800.api.response.notification.PushNotificationResponse;

/**
 * Implementation of M800Api.
 * <p/>
 * This implementation uses the JAX-RS reference implementation (Jersey) as it's REST client.
 * <p/>
 * To use, simply pass a new M800Config object to the constructor like so:
 * <pre>
 *   URI m800Uri = new URI("https://api.m800.com");
 *   M800PushApi api = new M800PushApiClient( new M800Config( "my_key", "my_secret", m800Uri ) );
 * </pre>
 *
 * @author rishijoshi
 * @since 1.1
 */
public class M800PushApiClient extends M800ApiClient implements M800PushApi {

  public M800PushApiClient(M800Config config) {
    super(config);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PushNotificationResponse send(String from, String to, String carrierId, String pushMessage,
                                       String appMessage) {
    final String endpoint = String.format("%s%s/push/%s",
                                          getApiUri().toString(), API_V1, to);
    PushCustomNotificationRequest request = new PushCustomNotificationRequest();
    request.setSender(String.format("%s@%s", from, carrierId));
    request.setPush(new Push());
    request.getPush().setContent(pushMessage);
    request.setAppMessage(new AppMessage());
    request.getAppMessage().setContent(appMessage);
    return getClient().target(endpoint)
        .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), PushNotificationResponse.class);
  }
}
