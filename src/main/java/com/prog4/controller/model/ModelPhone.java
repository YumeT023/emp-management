package com.prog4.controller.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModelPhone {
  private Long id;
  private String employee;
  private String code;
  private String number;
}
