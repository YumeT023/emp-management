package com.prog4.service;

import com.prog4.model.Phone;
import com.prog4.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhoneService {
  private final PhoneRepository repository;

  public Phone findByCodeAndNumber(String code, String number) {
    return repository.findByCodeAndNumber(code, number)
        .orElseThrow(() -> new RuntimeException(("Phone{code=%s,number=%s} is " +
            "not found.").formatted(code, number)));
  }

  public Phone save(Phone toSave) {
    return repository.save(toSave);
  }
}
