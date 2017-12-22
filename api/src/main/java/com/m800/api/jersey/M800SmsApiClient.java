/**
 * @since Jun 29, 2015
 */
package com.m800.api.jersey;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.m800.api.M800Config;
import com.m800.api.M800SmsApi;
import com.m800.api.request.sms.SmsMessageRequest;
import com.m800.api.response.sms.SmsMessageResponse;

/**
 * Implementation of M800Api.
 * <p/>
 * This implementation uses the JAX-RS reference implementation (Jersey) as it's REST client.
 * <p/>
 * To use, simply pass a new M800Config object to the constructor like so:
 * <pre>
 *   URI m800Uri = new URI("https://api.m800.com");
 *   M800SmsApi api = new M800SmsApiClient( new M800Config( "my_key", "my_secret", m800Uri ) );
 * </pre>
 *
 * @author KH Chan
 * @since 1.0
 */
public class M800SmsApiClient extends M800ApiClient implements M800SmsApi {

  public M800SmsApiClient(M800Config config) {
    super(config);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public SmsMessageResponse sendTextSms(String from, String to, String messageClass, String body) {
    SmsMessageRequest request = new SmsMessageRequest();
    request.setFrom(from);
    request.setType("text");
    request.setMessageClass(messageClass);
    request.setBody(body);
    return sendSms(to, request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SmsMessageResponse sendBinarySms(String from, String to, String messageClass,
                                          String body) {
    SmsMessageRequest request = new SmsMessageRequest();
    request.setFrom(from);
    request.setType("binary");
    request.setMessageClass(messageClass);
    request.setBody(body);
    return sendSms(to, request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SmsMessageResponse sendSms(String to, SmsMessageRequest request) {
    final String smsEndpoint = getApiUri().toString() + API_V1 + "/sms/" + to;
    return getClient().target(smsEndpoint)
        .request(MediaType.APPLICATION_JSON_TYPE)
        .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE), SmsMessageResponse.class);
  }
  

}
