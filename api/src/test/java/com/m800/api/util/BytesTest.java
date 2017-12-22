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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author KH Chan
 */
public class BytesTest {

  @Test
  public void testMd5String() {
    String msg = "this is a text string";
    String expected = "fead4144d92ccfd6b9fa6b73c18369be";
    String actual = Bytes.md5String(msg.getBytes());
    assertEquals(actual, expected);
  }

  @Test
  public void testHexString() {
    String msg = "just some text";
    String expected = "6a75737420736f6d652074657874";
    String actual = Bytes.toHexString(msg.getBytes());
    assertEquals(actual, expected);
  }
}
