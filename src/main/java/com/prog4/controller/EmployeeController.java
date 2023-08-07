package com.prog4.controller;

import com.prog4.controller.mapper.EmployeeMapper;
import com.prog4.controller.model.EmployeeFilter;
import com.prog4.controller.model.ModelEmployee;
import com.prog4.model.Company;
import com.prog4.model.JobRole;
import com.prog4.model.SocioPro;
import com.prog4.service.CompanyService;
import com.prog4.service.EmployeeService;
import com.prog4.service.JobRoleService;
import com.prog4.service.SocioProService;
import com.prog4.service.utils.csv.EmployeeHttpServletWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

  private final EmployeeService employeeService;
  private final JobRoleService jobRoleService;
  private final SocioProService socioProService;
  private final EmployeeMapper mapper;
  private final CompanyService companyService;

  @ModelAttribute("company")
  public Company getCompany() {
    return companyService.find();
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

  @GetMapping("/export-csv")
  public void exportAsCsv(
      @RequestParam(name = "firstname", required = false, defaultValue = "") String firstname,
      @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname,
      @RequestParam(name = "sex", required = false, defaultValue = "") String sex,
      @RequestParam(name = "job", required = false, defaultValue = "") Long job,
      @RequestParam(name = "hireDate", required = false, defaultValue = "") LocalDate hireDate,
      @RequestParam(name = "departureDate", required = false, defaultValue = "") LocalDate departureDate,
      @RequestParam(name = "sort", required = false, defaultValue = "ASC") Direction order,
      HttpServletResponse res
  ) throws IOException {
    res.setContentType("text/csv");
    res.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");
    EmployeeHttpServletWriter writer = new EmployeeHttpServletWriter(res);
    var employees = employeeService.findAllByCriteria(firstname, lastname, sex, job, order, hireDate, departureDate);
    writer.writeAll(employees);
  }

  @GetMapping
  public String getAllEmployees(
      @RequestParam(name = "firstname", required = false, defaultValue = "") String firstname,
      @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname,
      @RequestParam(name = "job", required = false, defaultValue = "") Long job,
      @RequestParam(name = "hireDate", required = false) LocalDate hireDate,
      @RequestParam(name = "departureDate", required = false) LocalDate departureDate,
      @RequestParam(name = "sort", required = false, defaultValue = "ASC") Direction order,
      @RequestParam(name = "sex", required = false, defaultValue = "") String sex,
      Model model
  ) {
    var filter = new EmployeeFilter(firstname, lastname, sex, job, order.name(), hireDate, departureDate);
    model.addAttribute("employees", employeeService.findAllByCriteria(
        filter.firstname(), filter.lastname(), filter.sex(), filter.job(), order, hireDate, departureDate));
    model.addAttribute("filter", filter);
    return "employee/list-employee";
  }

  @PostMapping("/add")
  public String addEmployee(@ModelAttribute ModelEmployee employee, Model model) throws IOException {
    try {
      var entity = mapper.toEntity(employee);
      var saved = employeeService.save(entity);
      return "redirect:/employees/show/".concat(saved.getMatriculate());
    } catch (Exception e) {
      log.info("error: {}", e.getMessage());
      model.addAttribute("employee", employee);
      model.addAttribute("error", e.getMessage());
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
  public String updateEmployee(@ModelAttribute ModelEmployee updated, Model model) throws IOException {
    try {
      var entity = mapper.toEntity(updated);
      employeeService.save(entity);
      return "redirect:/employees/show/" + entity.getMatriculate();
    } catch (Exception e) {
      log.info("error: {}", e.getMessage());
      model.addAttribute("employee", updated);
      model.addAttribute("error", e.getMessage());
      return "employee/update-employee";
    }
  }
}
