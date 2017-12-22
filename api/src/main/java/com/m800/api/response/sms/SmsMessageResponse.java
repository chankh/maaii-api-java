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
package com.m800.api.response.sms;

import com.m800.api.response.ErrorResponse;

import java.util.List;

/**
 * Represents a SMS send message response.
 *
 * @author KH Chan
 * @since 1.0
 */
public class SmsMessageResponse {

  private boolean success;
  private double totalCost;
  private int segmentCount;
  private List<Segment> segments;

  public static class Segment {

    private String messageId;
    private String status;
    private ErrorResponse error;

    public Segment() {

    }

    /**
     * Returns the message ID of this message segment.
     *
     * @return the message ID of this message segment.
     */
    public String getMessageId() {
      return messageId;
    }

    /**
     * Sets the message ID of this message segment.
     *
     * @param messageId the message ID of this message segment.
     */
    public void setMessageId(String messageId) {
      this.messageId = messageId;
    }

    /**
     * Returns the status of this message segment, whether sent or not.
     *
     * @return the status of this message segment, whether sent or not.
     */
    public String getStatus() {
      return status;
    }

    /**
     * Sets the status of this message segment, whether sent or not.
     *
     * @param status the status of this message segment, whether sent or not.
     */
    public void setStatus(String status) {
      this.status = status;
    }

    /**
     * Returns the error information of this message segment, if any.
     *
     * @return the error information of this message segment, if any.
     */
    public ErrorResponse getError() {
      return error;
    }

    /**
     * Sets the error information of this message segment, if any.
     *
     * @param error the error information of this message segment, if any.
     */
    public void setError(ErrorResponse error) {
      this.error = error;
    }
  }

  /**
   * Returns the status if the SMS message was sent successfully.
   *
   * @return <code>true</code> if all the message segments are sent successfully, <code>false</code>
   * otherwise.
   */
  public boolean isSuccess() {
    return success;
  }

  /**
   * Sets the status if the SMS message was sent successfully.
   *
   * @param success <code>true</code> if all the message segments are sent successfully,
   *                <code>false</code> otherwise.
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }

  /**
   * Returns the total cost for sending all the messages in USD.
   *
   * @return the total cost for sending all the messages in USD.
   */
  public double getTotalCost() {
    return totalCost;
  }

  /**
   * Sets the total cost for sending all the messages in USD.
   *
   * @param totalCost the total cost for sending all the messages in USD.
   */
  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  /**
   * Returns the number of segments created for the message in the request.
   *
   * @return the number of segments created for the message in the request.
   */
  public int getSegmentCount() {
    return segmentCount;
  }

  /**
   * Sets the number of segments created for the message in the request.
   *
   * @param segmentCount the number of segments created for the message in the request.
   */
  public void setSegmentCount(int segmentCount) {
    this.segmentCount = segmentCount;
  }

  /**
   * Returns the information of each message segment sent, in the order of message body.
   *
   * @return the information of each message segment sent, in the order of message body.
   */
  public List<Segment> getSegments() {
    return segments;
  }

  /**
   * Sets the information of each message segment sent, in the order of message body.
   *
   * @param segments the information of each message segment sent, in the order of message body.
   */
  public void setSegments(List<Segment> segments) {
    this.segments = segments;
  }
}
