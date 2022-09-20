package it.euris.ires.controllers;

import it.euris.ires.model.Employee;
import it.euris.ires.services.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class StandaloneControllerTests {

  @MockBean EmployeeService employeeService;

  @Autowired MockMvc mockMvc;

  @Test
  public void testfindAll() throws Exception {
    Employee employee = new Employee("Mario", "Rossi");
    List<Employee> employees = Collections.singletonList(employee);

    Mockito.when(employeeService.findAll()).thenReturn(employees);

    mockMvc
        .perform(get("/employee"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].firstName", Matchers.is("Mario")));
  }
}
