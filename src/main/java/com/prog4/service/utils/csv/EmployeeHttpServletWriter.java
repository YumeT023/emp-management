package com.prog4.service.utils.csv;

import com.prog4.model.Employee;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.joining;

@Slf4j
public class EmployeeHttpServletWriter extends HttpServletWriter<Employee> {
  public EmployeeHttpServletWriter(HttpServletResponse res) throws IOException {
    super(res);
  }

  @Override
  String[] getColumns() {
    return new String[]{
        "matriculate",
        "lastname",
        "firstname",
        "birthdate",
        "sex",
        "phone",
        "address",
        "personal email",
        "professional email",
        "hire date",
        "departure date",
        "cnaps number",
        "dependents",
        "national Card",
        "socio-pro category",
        "job"
    };
  }

  @Override
  String write(Employee subject) {
    List<String> values = Arrays.stream(new String[]{
        subject.getMatriculate(),
        subject.getLastname(),
        subject.getFirstname(),
        subject.getBirthdate().toString(),
        subject.getSex(),
        "[" + subject.getPhone().stream().map(p -> "+" + p.getCode() + " " + p.getNumber()).collect(joining(" - ")) + "]",
        subject.getAddress(),
        subject.getPersonalEmail(),
        subject.getProEmail(),
        subject.getHireDate().toString(),
        subject.getDepartureDate().toString(),
        subject.getCnapsNumber(),
        String.valueOf(subject.getDependents()),
        subject.getNationalCard().getNumber(),
        subject.getSocioProCategory().getName(),
        subject.getJobRole().getName()
    }).toList();
    return writeRow(values.toArray(new String[0]));
  }
}
