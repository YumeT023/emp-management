package com.prog4.controller.model;

import com.prog4.entity.Sex;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private String phone;
    private String address;
    private MultipartFile photo;
    private String personalEmail;
    private String proEmail;
    private LocalDate hireDate;
    private LocalDate departureDate;
    private int dependents;
    private Long socioPro;
    private Long jobRole;
    private List<String> postsList;
    private String cnapsNumber;
    private String cinNumber;
    private String cinPlaceIssue;
    private LocalDate cinDateIssue;
}
