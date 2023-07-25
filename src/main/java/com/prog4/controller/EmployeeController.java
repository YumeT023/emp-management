package com.prog4.controller;

import com.prog4.controller.mapper.EmployeeMapper;
import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.JobRole;
import com.prog4.entity.SocioPro;
import com.prog4.service.EmployeeService;
import com.prog4.service.JobRoleService;
import com.prog4.service.NationalCardService;
import com.prog4.service.SocioProService;
import com.prog4.service.validator.AlphanumericValidator;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

  private final EmployeeService employeeService;
  private final JobRoleService jobRoleService;
  private final SocioProService socioProService;
  private EmployeeMapper mapper;
  private final AlphanumericValidator alphanumericValidator;

  @GetMapping
  public String getAllEmployees(Model model) {
    model.addAttribute("employees", employeeService.findAll());
    return "employee/list-employee";
  }

  @ModelAttribute("socioProCategories")
  public List<SocioPro> getSocioProCategories() {
    return socioProService.findAll();
  }

  @ModelAttribute("jobRoles")
  public List<JobRole> getAvailableJobRole() {
    return jobRoleService.findAll();
  }

  @GetMapping("/add")
  public String showAddEmployeeForm(Model model) {
    model.addAttribute("employee", new ModelEmployee());
    return "employee/add-employee";
  }

  @PostMapping("/add")
  public String addEmployee(@ModelAttribute ModelEmployee employee, Model modelError) throws IOException {
    try {
      alphanumericValidator.accept(employee.getCnapsNumber());
      var entity = mapper.toEntity(employee);
      var saved = employeeService.save(entity);
      return "redirect:/employees/show/".concat(saved.getMatriculate());
    } catch (Exception e) {
      modelError.addAttribute("errorMessage", e.getMessage());
      return "employee/add-employee";
    }
  }

  @GetMapping("/show/{matriculate}")
  public String renderEmployeeDetails(@PathVariable String matriculate, Model model) {
    model.addAttribute("employee", employeeService.findByMatriculate(matriculate));
    return "employee/profiles";
  }

  @GetMapping("/{matriculate}/update")
  public String renderUpdateEmployeeForm(@PathVariable String matriculate, Model model) throws IOException {
    var employeeModel = mapper.toModel(employeeService.findByMatriculate(matriculate));
    log.info("spc to update: {}", employeeModel.getSocioPro());
    model.addAttribute("employee", employeeModel);
    return "employee/update-employee";
  }

  @PostMapping("/update")
  public String updateEmployee(@ModelAttribute ModelEmployee updated) throws IOException {
    var entity = mapper.toEntity(updated);
    employeeService.save(entity);
    return "redirect:/employees/show/" + entity.getMatriculate();
  }
}
