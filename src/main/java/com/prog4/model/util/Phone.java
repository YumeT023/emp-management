package com.prog4.model.util;

public record Phone(String code, String number) {
  public static Phone fromRaw(String raw) {
    var normalized = raw.split(",");
    return new Phone(normalized[0], normalized[1]);
  }
}
