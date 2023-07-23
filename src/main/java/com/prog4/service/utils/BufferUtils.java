package com.prog4.service.utils;

import java.util.Base64;

public class BufferUtils {
  public static String byteToBase64(byte[] bytes) {
    var encoder = Base64.getEncoder();
    return encoder.encodeToString(bytes);
  }
}
