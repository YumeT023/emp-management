package com.prog4.repository;

import com.prog4.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Optional<Employee> findByMatriculate(String matriculate);

  @Query(value = "SELECT * FROM employee " +
      "WHERE (:firstname IS NULL OR firstname LIKE CONCAT('%', :firstname, '%')) " +
      "AND (:lastname IS NULL OR lastname LIKE CONCAT('%', :lastname, '%')) " +
//      "AND (:sex IS NULL OR sex = CAST(:sex AS smallint)) " +
      "AND (:jobRole IS NULL OR job_role_id = :jobRole)",
      nativeQuery = true)
  List<Employee> findEmployeeByCriteria(
      @Param("firstname") String firstname,
      @Param("lastname") String lastname,
//      @Param("sex") Sex sex,
      @Param("jobRole") Long jobRole
  );
}
