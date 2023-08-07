package com.prog4.controller.model;

import com.prog4.model.Phone;
import com.prog4.model.util.Sex;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModelEmployee {
  private String matriculate;
  private String lastname;
  private String firstname;
  private LocalDate birthdate;
  private Sex sex;
  private List<ModelPhone> phones = new ArrayList<>();
  private String address;
  private MultipartFile photo;
  private String personalEmail;
  private String proEmail;
  private LocalDate hireDate;
  private LocalDate departureDate;
  private int dependents;
  private Long socioPro;
  private Long jobRole;
  private String cnapsNumber;
  private String cinNumber;
  private String cinPlaceIssue;
  private LocalDate cinDateIssue;
}
