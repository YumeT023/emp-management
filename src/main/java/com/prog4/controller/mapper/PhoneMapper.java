package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelPhone;
import com.prog4.model.Phone;
import com.prog4.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PhoneMapper {
  private final EmployeeService employeeService;

  public Phone toEntity(ModelPhone model, String employeeMatriculate) {
    var employee = employeeService.findByMatriculate(employeeMatriculate);
    return Phone.builder()
        .id(model.getId())
        .code(model.getCode())
        .number(model.getNumber())
        .employee(employee)
        .build();
  }

  public ModelPhone toModel(Phone entity) {
    return ModelPhone.builder()
        .id(entity.getId())
        .code(entity.getCode())
        .number(entity.getNumber())
        .employee(entity.getEmployee().getMatriculate())
        .build();
  }
}
