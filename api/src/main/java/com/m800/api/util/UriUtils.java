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
package com.m800.api.util;

import java.net.URI;
import java.net.URISyntaxException;

public class UriUtils {

  public static String removeAuthority(URI uri) {
    try {
      return "/" + new URI(uri.getScheme(), uri.getAuthority(), null, null, null).relativize(uri).toString();
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
