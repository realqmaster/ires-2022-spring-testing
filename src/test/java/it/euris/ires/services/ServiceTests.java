package it.euris.ires.services;

import it.euris.ires.dao.EmployeeRepository;
import it.euris.ires.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmployeeService.class)
public class ServiceTests {
  @Autowired EmployeeService service;

  @MockBean EmployeeRepository dao;

  @Test
  void testFindAllEmployees() {
    List<Employee> list = new ArrayList<>();
    Employee empOne = new Employee("Mario", "Rossi");
    Employee empTwo = new Employee("Paolo", "Bianchi");
    Employee empThree = new Employee("Pietro", "Neri");

    list.add(empOne);
    list.add(empTwo);
    list.add(empThree);

    when(dao.findAll()).thenReturn(list);

    // test
    List<Employee> empList = service.findAll();

    assertEquals(3, empList.size());
    verify(dao, times(1)).findAll();
  }

  @Test
  void testCreateOrSaveEmployee() {
    Employee employee = new Employee("Mario", "Rossi");

    service.save(employee);

    verify(dao, times(1)).save(employee);
  }
}
