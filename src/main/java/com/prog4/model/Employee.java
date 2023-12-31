package com.prog4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Table(name = "\"employee\"")
public class Employee {
  @Id
  private String matriculate;

  private String lastname;
  private String firstname;
  private LocalDate birthdate;
  private String sex;

  @OneToMany
  @JoinColumn(name = "employee_matriculate", referencedColumnName = "matriculate")
  private List<Phone> phone;
  private String address;
  @Column(columnDefinition = "text", nullable = true)
  private String photo;
  private String personalEmail;
  private String proEmail;
  private LocalDate hireDate;
  private LocalDate departureDate;
  private String cnapsNumber;
  private int dependents = 0;

  @OneToOne
  @JoinColumn(name = "national_card_id")
  private NationalCard nationalCard;

  @ManyToOne
  @JoinColumn(name = "socio_pro_category_id")
  private SocioPro socioProCategory;

  @ManyToOne
  @JoinColumn(name = "job_role_id")
  private JobRole jobRole;

  private static final String ID_FORMAT = "EMPLOYEE-%s";

  @PrePersist
  public void normalizeMatriculate() {
    UUID uuid = randomUUID();
    String uuidString = uuid.toString().replace("-", "");
    matriculate = ID_FORMAT.formatted(uuidString);
  }
}
