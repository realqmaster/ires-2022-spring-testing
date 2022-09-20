package it.euris.ires;

import it.euris.ires.controllers.EmployeeController;
import it.euris.ires.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SystemTests {

  @Autowired private EmployeeController controller;

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testCreateReadDelete() {
    String url = "http://localhost:" + port + "/employee";

    Employee employee = new Employee("Mario", "Rossi");
    ResponseEntity<Employee> entity = restTemplate.postForEntity(url, employee, Employee.class);

    Employee[] employees = restTemplate.getForObject(url, Employee[].class);
    Assertions.assertThat(employees).extracting(Employee::getFirstName).containsOnly("Mario");

    restTemplate.delete(url + "/" + entity.getBody().getId());
    Assertions.assertThat(restTemplate.getForObject(url, Employee[].class)).isEmpty();
  }

  @Test
  public void testErrorHandlingReturnsBadRequest() {

    String url = "http://localhost:" + port + "/wrong";

    try {
      restTemplate.getForEntity(url, String.class);
    } catch (HttpClientErrorException e) {
      Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
  }
}
