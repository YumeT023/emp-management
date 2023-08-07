package com.prog4.service.validator;

import java.util.List;

public interface Validator<T> {
  void validate(T subject);
  void validateMany(List<T> toValidate);

  // predicate utils
  boolean isNullOrBlank(String val);
}
