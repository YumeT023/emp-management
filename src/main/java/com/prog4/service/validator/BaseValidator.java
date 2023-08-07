package com.prog4.service.validator;

import java.util.List;

public abstract class BaseValidator<T> implements Validator<T> {
  @Override
  public void validateMany(List<T> toValidate) {
    toValidate.forEach(this::validate);
  }

  @Override
  public boolean isNullOrBlank(String val) {
    return val == null || val.isBlank();
  }
}
