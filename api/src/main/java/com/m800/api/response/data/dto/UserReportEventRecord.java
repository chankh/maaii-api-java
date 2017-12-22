package com.m800.api.response.data.dto;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Per Record for the Reported User HTTP API in the response.
 *
 * @author Angela Cheung
 * @since 17 Jun 2016 4:22 PM
 */
public class UserReportEventRecord {

  @JsonProperty("timestamp")
  private Long timestamp;

  @JsonProperty("username")
  private String username;

  @JsonProperty("country")
  private String country;

  @JsonProperty("carrier")
  private String carrier;

  @JsonProperty("device_id")
  private String deviceId;

  @JsonProperty("source_ip")
  private String sourceIp;

  @JsonProperty("ip_country")
  private String ipCountry;

  @JsonProperty("reason_message")
  private String reasonMessage;

  @JsonProperty("isp")
  private String isp;

  @JsonProperty("platform")
  private String platform;

  @JsonProperty("application")
  private String application;

  @JsonProperty("application_version")
  private String applicationVersion;

  @JsonProperty("source")
  private String source;

  @JsonProperty("user_creation_timestamp")
  private Long userCreationTimestamp;

  @JsonProperty("target_username")
  private String targetUsername;

  @JsonProperty("target_country")
  private String targetCountry;

  @JsonProperty("target_carrier")
  private String targetCarrier;

  @JsonProperty("target_platform")
  private String targetPlatform;

  @JsonProperty("target_application")
  private String targetApplication;

  @JsonProperty("target_application_version")
  private String targetApplicationVersion;

  @JsonProperty("target_creation_timestamp")
  private Long targetCreationTimestamp;

  public UserReportEventRecord() {

  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getSourceIp() {
    return sourceIp;
  }

  public void setSourceIp(String sourceIp) {
    this.sourceIp = sourceIp;
  }

  public String getIpCountry() {
    return ipCountry;
  }

  public void setIpCountry(String ipCountry) {
    this.ipCountry = ipCountry;
  }

  public String getReasonMessage() {
    return reasonMessage;
  }

  public void setReasonMessage(String reasonMessage) {
    this.reasonMessage = reasonMessage;
  }

  public String getIsp() {
    return isp;
  }

  public void setIsp(String isp) {
    this.isp = isp;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getApplicationVersion() {
    return applicationVersion;
  }

  public void setApplicationVersion(String applicationVersion) {
    this.applicationVersion = applicationVersion;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Long getUserCreationTimestamp() {
    return userCreationTimestamp;
  }

  public void setUserCreationTimestamp(Long userCreationTimestamp) {
    this.userCreationTimestamp = userCreationTimestamp;
  }

  public String getTargetUsername() {
    return targetUsername;
  }

  public void setTargetUsername(String targetUsername) {
    this.targetUsername = targetUsername;
  }

  public String getTargetCountry() {
    return targetCountry;
  }

  public void setTargetCountry(String targetCountry) {
    this.targetCountry = targetCountry;
  }

  public String getTargetCarrier() {
    return targetCarrier;
  }

  public void setTargetCarrier(String targetCarrier) {
    this.targetCarrier = targetCarrier;
  }

  public String getTargetPlatform() {
    return targetPlatform;
  }

  public void setTargetPlatform(String targetPlatform) {
    this.targetPlatform = targetPlatform;
  }

  public String getTargetApplication() {
    return targetApplication;
  }

  public void setTargetApplication(String targetApplication) {
    this.targetApplication = targetApplication;
  }

  public String getTargetApplicationVersion() {
    return targetApplicationVersion;
  }

  public void setTargetApplicationVersion(String targetApplicationVersion) {
    this.targetApplicationVersion = targetApplicationVersion;
  }

  public Long getTargetCreationTimestamp() {
    return targetCreationTimestamp;
  }

  public void setTargetCreationTimestamp(Long targetCreationTimestamp) {
    this.targetCreationTimestamp = targetCreationTimestamp;
  }
}
