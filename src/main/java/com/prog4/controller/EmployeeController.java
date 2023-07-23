package com.prog4.controller;

import com.prog4.controller.mapper.EmployeeMapper;
import com.prog4.controller.model.ModelEmployee;
import com.prog4.entity.Employee;
import com.prog4.entity.Post;
import com.prog4.entity.Sex;
import com.prog4.entity.SocioPro;
import com.prog4.service.EmployeeService;
import com.prog4.service.PostsService;
import com.prog4.service.SocioProService;
import com.prog4.service.validator.AlphanumericValidator;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
public class EmployeeController {

  private final EmployeeService employeeService;
  private final PostsService postsService;
  private final SocioProService socioProService;
  private EmployeeMapper mapper;
  private final AlphanumericValidator validator;

  @GetMapping
  public String getAllEmployees(Model model) {
    model.addAttribute("employees", employeeService.findAll());
    return "employee/list_employee";
  }

  @ModelAttribute("sexOptions")
  public Sex[] getSexOptions() {
    return Sex.values();
  }

  @ModelAttribute("suggestedSocioProOptions")
  public List<SocioPro> getSuggestedSocioProOptions() {
    return socioProService.findAll();
  }

  @ModelAttribute("suggestedPostProOptions")
  public List<Post> getSuggestedPostProOptions() {
    return postsService.findAll();
  }

  @GetMapping("/add")
  public String showAddEmployeeForm(Model model) {
    model.addAttribute("employee", new ModelEmployee());
    return "employee/add-employee";
  }

  @PostMapping("/add")
  public String addEmployee(@ModelAttribute ModelEmployee employee, Model modelError) throws IOException {
    try {
      if (validator.checkIfAlphanumeric(employee.getCinNumber())) {
        var saved = employeeService.save(mapper.toEntity(employee));
        return "redirect:/employees/" + saved.getMatriculate();
      }
      modelError.addAttribute("cnapsError", "cnaps number must be alphanumeric only [a-zA-Z0-9]");
      return "employee/add-employee";
    } catch (DataIntegrityViolationException ex) {
      modelError.addAttribute("errorMessage", "Registration number must be unique.");
      return "employee/add-employee";
    }
  }


  @GetMapping("/{matriculate}")
  public String renderEmployeeDetails(@PathVariable String matriculate, Model model) {
    model.addAttribute("employee", employeeService.findByMatriculate(matriculate));
    return "employee/profiles";
  }

  @GetMapping("/{matriculate}/update")
  public String renderUpdateEmployeeForm(@PathVariable String matriculate, Model model) {
    model.addAttribute("newEmployee", employeeService.findByMatriculate(matriculate));
    return "employee/update-employee";
  }

//  @PostMapping("/update")
//  public String updateEmployee(@ModelAttribute("newEmployee") Employee modelEmployee) throws IOException {
//    SocioPro socioPro = socioProService.getByCategory(modelEmployee.getCateSocioPro().getCategories());
//    socioPro.setCategories(modelEmployee.getCateSocioPro().getCategories());
//    Employee employee = mapper.toUpdate(socioPro, modelEmployee);
//    return "redirect:/employees/" + employee.getMatriculate();
//  }

  @GetMapping("/export-csv")
  public void exportCsv(HttpServletResponse response) throws IOException {
    List<Employee> employees = employeeService.findAll();

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");

    try (PrintWriter writer = response.getWriter()) {
      writer.println("First Name,Last Name,Date of Birth,Registration Number");
      for (Employee employee : employees) {
        writer.println(
            employee.getFirstname() + "," +
                employee.getLastname() + "," +
                employee.getBirthdate() + "," +
                employee.getSex() + "," +
                employee.getPhone() + "," +
                employee.getAddress() + "," +
                employee.getPersonalEmail() + "," +
                employee.getProEmail() + "," +
                employee.getHireDate() + "," +
                employee.getDepartureDate() + "," +
                employee.getDependents() + "," +
                employee.getSocioProCategory().getCategories() + "," +
                employee.getCin().getNumber() + "," +
                employee.getCin().getDate() + "," +
                employee.getCin().getPlace() + "," +
                employee.getCnapsNumber()
        );
      }
    }
  }

}
