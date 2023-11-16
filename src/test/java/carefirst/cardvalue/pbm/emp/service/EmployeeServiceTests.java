package carefirst.cardvalue.pbm.emp.service;

import carefirst.cardvalue.pbm.emp.model.Employee;
import carefirst.cardvalue.pbm.emp.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .id(1L)
                .firstName("Rob")
                .lastName("Frank")
                .emailAddress("rob@msn.com")
                .build();
    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        given(employeeRepository.save(employee)).willReturn(employee);
        Employee savedEmployee = employeeService.createNewEmployee(employee);
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Tony")
                .lastName("Stark")
                .emailAddress("tony@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));
        List<Employee> employeeList = employeeService.getEmployees();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        Employee savedEmployee = employeeService.findEmployee(employee.getId()).get();
        // then
        assertThat(savedEmployee).isNotNull();
    }
}
