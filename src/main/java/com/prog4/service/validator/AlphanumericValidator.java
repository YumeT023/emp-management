package com.prog4.service.validator;

import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AlphanumericValidator implements Consumer<String> {
  private static final String alphaNumericRegExp = "^[a-zA-Z0-9]+$";

  @Override
  public void accept(String s) {
    if (!s.matches(alphaNumericRegExp)) {
      throw new RuntimeException("Provided value is not a valid alphanumeric");
    }
  }
}
