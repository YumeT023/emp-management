package com.prog4.repository;

import com.prog4.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Optional<Employee> findByMatriculate(String matriculate);
}
