package com.prog4.service;

import com.prog4.model.Company;
import com.prog4.model.Employee;
import com.prog4.repository.CompanyRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompanyService {
  private static final Long ID = 1L;
  private final CompanyRepository repository;

  public Company find() {
    Optional<Company> optional = repository.findById(ID);
    return optional.orElseThrow(() -> new RuntimeException("Company not found"));
  }
}
