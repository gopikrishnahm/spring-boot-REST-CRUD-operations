package carefirst.cardvalue.pbm.emp.repository;

import carefirst.cardvalue.pbm.emp.model.Employee;
import carefirst.cardvalue.pbm.emp.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for saveEmployee
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){
        Employee employee = Employee.builder()
                .firstName("Jack")
                .lastName("Jill")
                .emailAddress("jack@msn.com")
                .build();
        employeeRepository.save(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){
        Employee employee = employeeRepository.findById(1).get();
        Assertions.assertThat(employee.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){
        List<Employee> employees = employeeRepository.findAll();
        Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){
        Employee employee = employeeRepository.findById(1).get();
        employee.setEmailAddress("emailUpdated@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);
        Assertions.assertThat(employeeUpdated.getEmailAddress()).isEqualTo("emailUpdated@gmail.com");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        Employee employee = employeeRepository.findById(1).get();
        //employeeRepository.delete(employee);
        employeeRepository.deleteById(1);
        Employee employee1 = null;
        Optional<Employee> optionalEmployee = employeeRepository.findById(1);
        if(optionalEmployee.isPresent()){
            employee1 = optionalEmployee.get();
        }
        Assertions.assertThat(employee1).isNull();
    }
}
