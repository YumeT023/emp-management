package com.prog4.service.validator;

public abstract class BaseValidator<T> implements Validator<T> {

  abstract public void validate(T subject);

  @Override
  public boolean isNullOrBlank(String val) {
    return val == null || val.isBlank();
  }
}
