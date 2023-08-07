package com.prog4.controller.model;

import java.time.LocalDate;

public record EmployeeFilter(
    String firstname,
    String lastname,
    String sex,
    Long job,
    String sort,
    LocalDate hireDate,
    LocalDate departureDate
) {
}
