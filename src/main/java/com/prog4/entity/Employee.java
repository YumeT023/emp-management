package com.prog4.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.UUID.randomUUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Employee {
  @Id
  private String matriculate;

  private String lastname;
  private String firstname;
  private LocalDate birthdate;
  private Sex sex;
  private String phone;
  private String address;
  @Column(columnDefinition = "text", nullable = true)
  private String photo;
  private String personalEmail;
  private String proEmail;
  private LocalDate hireDate;
  private LocalDate departureDate;
  private String cnapsNumber;
  private int dependents = 0;


  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
  private SocioPro socioProCategory;

  @OneToOne(cascade = CascadeType.ALL)
  private NationalCard cin;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Post> postsList;


  private static final String ID_FORMAT = "EMPLOYEE-%s";

  @PrePersist
  public void normalizeMatriculate() {
    UUID uuid = randomUUID();
    String uuidString = uuid.toString().replace("-", "");
    matriculate = ID_FORMAT.formatted(uuidString);
  }
}
