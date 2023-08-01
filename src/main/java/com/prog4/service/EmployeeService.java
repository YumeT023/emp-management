package com.prog4.service;

import com.prog4.model.Employee;
import com.prog4.repository.EmployeeRepository;
import com.prog4.service.validator.EmployeeValidator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import static java.time.format.DateTimeFormatter.ISO_DATE;

@AllArgsConstructor
@Service
public class EmployeeService {
  private final EmployeeRepository repository;
  private final NationalCardService ncService;
  private final EmployeeValidator validator;

  public List<Employee> findAll() {
    return repository.findAll();
  }

  public List<Employee> findAllByCriteria(
      String firstname,
      String lastname,
      Long jobRole,
      Direction direction,
      LocalDate hireDate,
      LocalDate departureDate
  ) {
    Sort sort = Sort.by(direction, "lastname", "firstname", "job.name");
    Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, sort);
    var _hireDate = hireDate != null ? hireDate.format(ISO_DATE) : null;
    var _depDate = departureDate != null ? departureDate.format(ISO_DATE) : null;
    return repository.findEmployeeByCriteria(
        firstname, lastname, jobRole, _hireDate, _depDate, pageable);
  }

  public Employee findByMatriculate(String matriculate) {
    Optional<Employee> optional = repository.findByMatriculate(matriculate);
    return optional.orElseThrow(() -> new RuntimeException("Employee.matriculate=" + matriculate + " not found"));
  }

  public Employee save(Employee toSave) {
    validator.accept(toSave);
    ncService.save(toSave.getNationalCard());
    return repository.save(toSave);
  }
}
