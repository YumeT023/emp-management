package com.prog4.service.validator;

import com.prog4.model.Phone;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PhoneValidator extends BaseValidator<Phone> {
  @Override
  public void validate(Phone phone) {
    var message = new StringBuilder();

    if (phone.getCode() == null) {
      message.append("country code is mandatory.");
    } else if (phone.getCode().isEmpty() || phone.getCode().length() > 4) {
      message.append("country code length must be > 0 and < 5. ");
    } else if (phone.getCode().matches("[^0-9]")) {
      message.append("country code must only contains digits. ");
    }

    if (phone.getNumber() == null) {
      message.append("number is mandatory.");
    } else if (phone.getNumber().length() != 9) {
      message.append("number must be 9 digit long.");
    } else if (phone.getNumber().matches("[^0-9]]")) {
      message.append("number must only contains digits.");
    }

    if (!message.isEmpty()) {
      throw new RuntimeException(message.toString());
    }
  }

  @Override
  public void validateMany(List<Phone> toValidate) {
    var distinct = toValidate.stream().map(Phone::toString).distinct().toList();
    if (distinct.size() != toValidate.size()) {
      throw new RuntimeException("phone numbers must not be identical");
    }
    super.validateMany(toValidate);
  }
}
