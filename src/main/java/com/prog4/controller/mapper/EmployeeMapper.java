package com.prog4.controller.mapper;

import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.Employee;
import com.prog4.entity.SocioPro;
import com.prog4.service.CnapsService;
import com.prog4.service.EmployeeService;
import com.prog4.service.NationalCardService;
import com.prog4.service.PostsService;
import com.prog4.service.SocioProService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.prog4.service.utils.BufferUtils.byteToBase64;

@Component
@AllArgsConstructor
public class EmployeeMapper {

  private PostsService postsService;
  private NationalCardService nationalCardService;
  private CnapsService cnapsService;
  private EmployeeService service;
  private SocioProService socioProService;

  public Employee toEntity(ModelEmployee model) throws IOException {
    String photo = byteToBase64(model.getPhoto().getBytes());
    SocioPro socioProCategory = model.getSocioPro() != null ?
        socioProService.getById(model.getSocioPro())
        : null;

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
        .postsList(List.of())
        .socioProCategory(socioProCategory)
        .photo(photo)
        .build();
  }

//  public Employee toEntity(ModelEmployee employee) throws IOException {
//    SocioPro socioPro = employee.getSocioPro() != null ? socioProService.getById(employee.getSocioPro()) : null;
//    NationalCard nationalCard = nationalCardService.getByNumber(employee.getCinNumber());
//    nationalCard.setDate(employee.getCinDate());
//    nationalCard.setPlace(employee.getCinPlace());
//    nationalCard.setNumber(employee.getCinNumber());
//    Cnaps cnaps = cnapsService.getByNumber(employee.getNbrCnaps());
//
//    List<Post> postList = new ArrayList<>();
//    for (String post : employee.getPostsList()) {
//      Post tmp = postsService.getByName(post);
//      postList.add(tmp);
//    }
//    service.save(employee1);
//    return employee1;
//  }

//  public Employee toUpdate(SocioPro socioPro, Employee employee) throws IOException {
//    var toUpdate = service.findByMatriculate(employee)
//
//    Employee employee1 = service.findByRegisterNumber(employee.getRegistrationNbr());
//    employee1.setSex(employee.getSex());
//    employee1.setPostsList(employee.getPostsList());
//    employee1.setCin(employee.getCin());
//    employee1.setAddress(employee.getAddress());
//    employee1.setDateOfBirth(employee.getDateOfBirth());
//    employee1.setNbrCnaps(employee.getNbrCnaps());
//    service.save(employee1);
//    return employee1;
//  }
}
