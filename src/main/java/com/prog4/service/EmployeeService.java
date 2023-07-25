package com.prog4.service;

import com.prog4.model.Employee;
import com.prog4.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeService {
  private final EmployeeRepository repository;
  private final NationalCardService ncService;

  public List<Employee> findAllByCriteria(
      String firstname,
      String lastname,
      Long jobRole,
      Direction direction
  ) {
    Sort sort = Sort.by(direction, "lastname", "firstname", "job.name");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    return repository.findEmployeeByCriteria(firstname, lastname, jobRole, pageable);
  }

  public Employee findByMatriculate(String matriculate) {
    Optional<Employee> optional = repository.findByMatriculate(matriculate);
    return optional.orElseThrow(() -> new RuntimeException("Employee.matriculate=" + matriculate + " not found"));
  }

  public Employee save(Employee toSave) {
    ncService.save(toSave.getNationalCard());
    return repository.save(toSave);
  }
}
