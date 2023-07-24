package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.Employee;
import com.prog4.entity.JobRole;
import com.prog4.entity.NationalCard;
import com.prog4.entity.SocioPro;
import com.prog4.service.JobRoleService;
import com.prog4.service.SocioProService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.prog4.service.utils.BufferUtils.byteToBase64;

@Component
@AllArgsConstructor
public class EmployeeMapper {

  private JobRoleService jobRoleService;
  private SocioProService socioProService;

  public Employee toEntity(ModelEmployee model) throws IOException {
    String photo = byteToBase64(model.getPhoto().getBytes());
    SocioPro socioProCategory = model.getSocioPro() != null ?
        socioProService.getById(model.getSocioPro())
        : null;
    JobRole jobRole = jobRoleService.getById(model.getJobRole());
    NationalCard nationalCard = NationalCard.builder()
        .dateIssue(model.getCinDateIssue())
        .placeIssue(model.getCinPlaceIssue())
        .number(model.getCinNumber())
        .build();

    return Employee.builder()
        .matriculate(model.getMatriculate())
        .dependents(model.getDependents())
        .firstname(model.getFirstName())
        .lastname(model.getLastName())
        .personalEmail(model.getPersonalEmail())
        .proEmail(model.getProEmail())
        .proEmail(model.getProEmail())
        .phone(model.getPhone())
        .hireDate(model.getHireDate())
        .departureDate(model.getDepartureDate())
        .address(model.getAddress())
        .birthdate(model.getBirthdate())
        .cnapsNumber(model.getCnapsNumber())
        .sex(model.getSex())
        .nationalCard(nationalCard)
        .jobRole(jobRole)
        .socioProCategory(socioProCategory)
        .photo(photo)
        .build();
  }
}
