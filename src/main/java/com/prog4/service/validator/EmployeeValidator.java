package com.prog4.service.validator;

import com.prog4.model.Employee;
import com.prog4.model.util.Phone;
import com.prog4.repository.EmployeeRepository;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EmployeeValidator implements Consumer<Employee> {
  private final EmployeeRepository repository;

  @Override
  public void accept(Employee employee) {
    Phone phone = Phone.fromRaw(employee.getPhone());

    if (phone.number().length() != 10) {
      throw new RuntimeException("Phone number should be of length 10");
    }

    var employees = repository.findAll();
    employees.forEach(e -> {
      Phone ePhone = Phone.fromRaw(e.getPhone());
      boolean isPhoneIdentical = phone.code().equals(ePhone.code()) && phone.number().equals(ePhone.number());
      if (!e.getMatriculate().equals(employee.getMatriculate()) && isPhoneIdentical) {
        throw new RuntimeException("Employee with the phone '" + employee.getPhone().replace(",", "+ ") + "' already exists");
      }
    });
  }
}
