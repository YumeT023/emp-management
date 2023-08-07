package com.prog4.repository;

import com.prog4.model.Phone;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
  Optional<Phone> findByCodeAndNumber(String code, String number);
}
