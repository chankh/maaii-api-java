/**
 * @since Jun 29, 2015
 */
package com.m800.api;

import com.m800.api.response.notification.PushNotificationResponse;

/**
 * @author rishijoshi
 * @since 1.1
 */
public interface M800PushApi {

  /**
   * Sends a push notification message.
   *
   * @param from        username of sender for the message.
   * @param to          username of recipient for the message.
   * @param carrierId   carrier identifier.
   * @param pushMessage some notification text, will be sent using push channel, if available.
   * @param appMessage  some notification text, will be sent using IM channel
   * @return response from server.
   */
  PushNotificationResponse send(String from, String to, String carrierId, String pushMessage,
                                String appMessage);

}
