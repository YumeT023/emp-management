package com.prog4.repository;

import com.prog4.model.SocioPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocioProRepository extends JpaRepository<SocioPro,Long> {
    SocioPro findSocioProByNameIgnoreCase(String name);
}
