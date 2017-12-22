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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Nicolas Berthet
 * @since 1.0
 */
public class Bytes {

  private static char[] hexDigits = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };

  public static String toHexString(byte[] bytes) {

    final int length = bytes.length << 1;
    final char[] buffer = new char[length];

    int p = -1;
    for (byte b : bytes) {
      buffer[++p] = hexDigits[(b & 0xF0) >>> 4];
      buffer[++p] = hexDigits[b & 0xF];
    }

    return new String(buffer, 0, length);
  }

  public static String md5String(byte[] bytes) {
    if (bytes == null || bytes.length == 0) {
      return null;
    }

    try {
      return toHexString(MessageDigest.getInstance("MD5").digest(bytes));
    } catch (NoSuchAlgorithmException e) {
      // ignored
    }


    return "";
  }
}
