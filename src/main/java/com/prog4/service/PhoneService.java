package com.prog4.service;

import com.prog4.model.Phone;
import com.prog4.repository.PhoneRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {
  private final PhoneRepository repository;

  public Optional<Phone> findByCodeAndNumber(String code, String number) {
    return repository.findByCodeAndNumber(code, number);
  }

  public Phone getByCodeAndNumber(String code, String number) {
    return repository.findByCodeAndNumber(code, number)
        .orElseThrow(() -> new RuntimeException(("Phone{code=%s,number=%s} is " +
            "not found.").formatted(code, number)));
  }

  public void ensureNoDuplicateWithDifferentOwner(Phone phone) {
    var existent = findByCodeAndNumber(phone.getCode(), phone.getNumber());
    if (existent.isPresent()) {
      var existentPhoneOwner = existent.get().getEmployee().getMatriculate();
      var subjectOwner = phone.getEmployee().getMatriculate();
      if (!existentPhoneOwner.equals(subjectOwner)) {
        throw new RuntimeException("Phone " + phone + " is already owned by employee.matriculate=" + existentPhoneOwner);
      }
    }
  }

  public Phone save(Phone toSave) {
    return repository.save(toSave);
  }
}
