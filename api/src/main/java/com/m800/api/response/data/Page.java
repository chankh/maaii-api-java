package com.m800.api.response.data;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Angela Cheung
 * @since 23 Jun 2016 06:13 PM
 */
public class Page<T> implements Serializable {

  @JsonProperty("page_number")
  private Integer pageNumber;
  @JsonProperty("page_size")
  private Integer pageSize;
  @JsonProperty("offset")
  private Long offset;
  @JsonProperty("content")
  private List<T> content;
  @JsonProperty("number_of_elements")
  private int numberOfElements;
  @JsonProperty("total_pages")
  private int totalPages;
  @JsonProperty("total_elements")
  private int totalElements;

  public Page() {
  }

  public int getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(int numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public Long getOffset() {
    return offset;
  }

  public void setOffset(Long offset) {
    this.offset = offset;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public int getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(int totalElements) {
    this.totalElements = totalElements;
  }
}
