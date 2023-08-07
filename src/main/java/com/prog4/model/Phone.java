package com.prog4.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@EqualsAndHashCode
@Table(name = "\"phone\"", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "number"}))
public class Phone {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private String code;

  @ManyToOne
  @JoinColumn(name = "employee_matriculate", referencedColumnName = "matriculate")
  private Employee employee;

  @Override
  public String toString() {
    var buf = new StringBuilder();
    buf.append("+")
        .append(code)
        .append(" ")
        .append(number);
    return buf.toString();
  }
}
