package com.prog4.service.validator;

import com.prog4.model.Employee;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EmployeeValidator extends BaseValidator<Employee> {
  private final PhoneValidator phoneValidator;

  @Override
  public void validate(Employee employee) {
    StringBuilder error = new StringBuilder();

    if (employee.getBirthdate() == null) {
      error.append("Birthdate is mandatory. ");
    } else if (employee.getBirthdate().isAfter(LocalDate.now())) {
      error.append("Birthdate could not be after today. ");
    }
    if (isNullOrBlank(employee.getLastname())) {
      error.append("Last name is mandatory. ");
    }
    if (isNullOrBlank(employee.getFirstname())) {
      error.append("First name is mandatory. ");
    }
    if (employee.getPhone() == null || employee.getPhone().isEmpty()) {
      error.append("At least one phone number is expected. ");
    } else {
      try {
        phoneValidator.validateMany(employee.getPhone());
      } catch (Exception e) {
        error.append(e.getMessage());
      }
    }
    if (isNullOrBlank(employee.getAddress())) {
      error.append("Address is mandatory. ");
    }
    if (employee.getSex() == null) {
      error.append("Sex is mandatory. ");
    }
    if (employee.getNationalCard() == null) {
      error.append("CIN is mandatory. ");
    }
    if (employee.getSocioProCategory() == null) {
      error.append("Socio-professional category is mandatory. ");
    }
    if (employee.getJobRole() == null) {
      error.append("At least one position is expected. ");
    }
    if (employee.getHireDate() == null) {
      error.append("Entrance date is mandatory. ");
    } else if (employee.getHireDate().isBefore(employee.getHireDate())) {
      error.append("Entrance date can't be before birthDate. ");
    } else if (employee.getHireDate().isAfter(LocalDate.now())) {
      error.append("Entrance date can't be after today. ");
    }
    if (employee.getHireDate() != null && employee.getDepartureDate() != null && employee.getDepartureDate().isBefore(employee.getHireDate())) {
      error.append("Departure date can't be before entrance date. ");
    }
    if (isNullOrBlank(employee.getProEmail())) {
      error.append("Professional email is mandatory. ");
    }
    if (isNullOrBlank(employee.getPersonalEmail())) {
      error.append("Personal email is mandatory. ");
    }
    if (isNullOrBlank(employee.getCnapsNumber())) {
      error.append("Cnaps is mandatory. ");
    }
    if (!error.isEmpty()) {
      throw new RuntimeException(error.toString());
    }
  }
}
