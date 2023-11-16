package carefirst.cardvalue.pbm.emp.controller;

import carefirst.cardvalue.pbm.emp.model.Employee;
import carefirst.cardvalue.pbm.emp.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void test_saveEmployee() throws Exception{
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Jack")
                .lastName("Tom")
                .emailAddress("jack.t@msn.com")
                .build();
        // given
        given(employeeService.createNewEmployee(any(Employee.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(post("/api/v1/employees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        // then
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLastName())))
                .andExpect(jsonPath("$.emailAddress",
                        is(employee.getEmailAddress())));
    }

    // JUnit test for getting All employees from REST API
    @Test
    public void test_getEmployeesList() throws Exception{
        // given
        List<Employee> listOfEmployees = new ArrayList<>();
        listOfEmployees.add(Employee.builder().firstName("John").lastName("Swiot").emailAddress("john.s@msn.com").build());
        listOfEmployees.add(Employee.builder().firstName("Tony").lastName("Stark").emailAddress("tony.s@msn.com").build());
        given(employeeService.getEmployees()).willReturn(listOfEmployees);

        // when
        ResultActions response = mockMvc.perform(get("/api/v1/employees/"));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfEmployees.size())));
    }

    // JUnit test for geting an Employee from REST API
    @Test
    public void test_getEmployeesObject() throws Exception{
        // given
        String employeeId = "1";
        Employee employee = Employee.builder()
                .firstName("Sean")
                .lastName("C")
                .emailAddress("sean@msn.com")
                .build();
        given(employeeService.findEmployee(Long.getLong(employeeId))).willReturn(Optional.of(employee));

        // when
        ResultActions response = mockMvc.perform(get("/api/v1/employees/{id}", employeeId));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.emailAddress", is(employee.getEmailAddress())));
    }


    @Test
    public void test_getEmployeesObject_whenEmpty() throws Exception{
        // given
        String employeeId = "1";
        Employee employee = Employee.builder()
                .firstName("R")
                .lastName("Bob")
                .emailAddress("bob@msn.com")
                .build();
        given(employeeService.findEmployee(Long.getLong(employeeId))).willReturn(Optional.empty());

        // when
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    // JUnit test for delete employee REST API
   //@Test
    public void test_whenDeleteEmployee() throws Exception{
        // given
        String employeeId = "1";
        willDoNothing().given(employeeService).deleteEmployee(Long.getLong(employeeId));

        // when
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employeeId));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
