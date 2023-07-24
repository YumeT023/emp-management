package com.prog4.service;

import com.prog4.entity.SocioPro;
import com.prog4.repository.SocioProRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SocioProService {

  private final SocioProRepository repository;

  public List<SocioPro> findAll() {
    return repository.findAll();
  }

  public SocioPro getById(Long id) {
    Optional<SocioPro> optional = repository.findById(id);
    return optional.orElseThrow(() -> new RuntimeException("SocioPro#" + id + " not found"));
  }

  public SocioPro save(SocioPro toSave) {
    return repository.save(toSave);
  }
}
