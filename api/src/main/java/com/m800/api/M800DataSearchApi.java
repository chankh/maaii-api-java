package com.m800.api;

import java.util.Map;

import com.m800.api.response.data.Page;

/**
 * @author Angela Cheung
 * @since 23 Jun 2016 12:15 PM
 */
public interface M800DataSearchApi<T> {

  Page<T> send(Map<String, Object> queryParam);

}
