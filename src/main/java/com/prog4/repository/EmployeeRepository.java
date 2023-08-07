package com.prog4.repository;

import com.prog4.model.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Optional<Employee> findByMatriculate(String matriculate);

  @Query(value = "SELECT e.* FROM employee e " +
      "LEFT JOIN job_role job on e.job_role_id = job.id " +
      "WHERE (:firstname IS NULL OR e.firstname LIKE CONCAT('%', :firstname, '%')) " +
      "AND (:lastname IS NULL OR e.lastname LIKE CONCAT('%', :lastname, '%')) " +
      "AND (:hireDate IS NULL OR e.hire_date >= cast(:hireDate as date)) " +
      "AND (:departureDate IS NULL OR e.departure_date <= cast(:departureDate as date)) " +
      "AND (:jobRole IS NULL OR e.job_role_id = :jobRole)" +
      "AND (:sex IS NULL OR e.sex = :sex)",
      nativeQuery = true)
  List<Employee> findEmployeeByCriteria(
      @Param("firstname") String firstname,
      @Param("lastname") String lastname,
      @Param("sex") String sex,
      @Param("jobRole") Long jobRole,
      @Param("hireDate") String hireDate,
      @Param("departureDate") String departureDate,
      Pageable pageable
  );
}
