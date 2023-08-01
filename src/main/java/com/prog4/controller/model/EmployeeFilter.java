package com.prog4.controller.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeeFilter {
  private String firstname;
  private String lastname;
  private Long job;
  private String sort;
  private LocalDate hireDate;
  private LocalDate departureDate;
}
