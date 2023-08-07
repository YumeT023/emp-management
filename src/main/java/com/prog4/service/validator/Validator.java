package com.prog4.service.validator;

public interface Validator<T> {
  void validate(T subject);

  // predicate utils
  boolean isNullOrBlank(String val);
}
