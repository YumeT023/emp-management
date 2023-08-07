package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.model.Employee;
import com.prog4.model.JobRole;
import com.prog4.model.NationalCard;
import com.prog4.model.SocioPro;
import com.prog4.model.util.Phone;
import com.prog4.model.util.Sex;
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
    String photo = model.getPhoto() != null ? byteToBase64(model.getPhoto().getBytes()) : null;
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
        .firstname(model.getFirstname())
        .lastname(model.getLastname())
        .personalEmail(model.getPersonalEmail())
        .proEmail(model.getProEmail())
        .proEmail(model.getProEmail())
        .phone(model.getPhoneCode() + "," + model.getPhoneNumber())
        .hireDate(model.getHireDate())
        .departureDate(model.getDepartureDate())
        .address(model.getAddress())
        .birthdate(model.getBirthdate())
        .cnapsNumber(model.getCnapsNumber())
        .sex(model.getSex().name())
        .nationalCard(nationalCard)
        .jobRole(jobRole)
        .socioProCategory(socioProCategory)
        .photo(photo)
        .build();
  }

  public ModelEmployee toModel(Employee entity) throws IOException {
    var nationalCard = entity.getNationalCard();

    var phone = Phone.fromRaw(entity.getPhone());
    return ModelEmployee.builder()
        .matriculate(entity.getMatriculate())
        .matriculate(entity.getMatriculate())
        .dependents(entity.getDependents())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .personalEmail(entity.getPersonalEmail())
        .proEmail(entity.getProEmail())
        .proEmail(entity.getProEmail())
        .phoneCode(phone.code())
        .phoneNumber(phone.number())
        .hireDate(entity.getHireDate())
        .departureDate(entity.getDepartureDate())
        .address(entity.getAddress())
        .birthdate(entity.getBirthdate())
        .cnapsNumber(entity.getCnapsNumber())
        .sex(Sex.valueOf(entity.getSex()))
        .cinDateIssue(nationalCard.getDateIssue())
        .cinPlaceIssue(nationalCard.getPlaceIssue())
        .cinNumber(nationalCard.getNumber())
        .jobRole(entity.getJobRole().getId())
        .socioPro(entity.getSocioProCategory().getId())
        .build();
  }
}
