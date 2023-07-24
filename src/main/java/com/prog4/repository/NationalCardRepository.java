package com.prog4.repository;

import com.prog4.entity.JobRole;
import com.prog4.entity.NationalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalCardRepository extends JpaRepository<NationalCard, Long> {
}
