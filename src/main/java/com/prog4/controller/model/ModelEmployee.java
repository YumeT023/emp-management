package com.prog4.controller.model;

import com.prog4.entity.*;
import java.time.LocalDate;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModelEmployee {
    private String matriculate;
    private String lastName;
    private String firstName;
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
    private String cinNumber;
    private String cinDate;
    private String cinPlace;
    private List<String> postsList;
    private String cnapsNumber;
}
