package com.prog4.service;

import com.prog4.model.NationalCard;
import com.prog4.repository.NationalCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NationalCardService {
  private final NationalCardRepository repository;

  public NationalCard save(NationalCard toSave) {
    return repository.save(toSave);
  }
}
