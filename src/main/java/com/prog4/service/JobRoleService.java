package com.prog4.service;

import com.prog4.entity.JobRole;
import com.prog4.repository.JobRoleRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JobRoleService {

  private final JobRoleRepository repository;

  public List<JobRole> findAll() {
    return repository.findAll();
  }

  public JobRole getById(Long id) {
    Optional<JobRole> optional = repository.findById(id);
    return optional.orElseThrow(() -> new RuntimeException("JobRole#" + id + " not found"));
  }
}
