package carefirst.cardvalue.pbm.emp.service;

import carefirst.cardvalue.pbm.emp.enums.StatusCode;
import carefirst.cardvalue.pbm.emp.exception.EmployeeException;
import carefirst.cardvalue.pbm.emp.model.Employee;
import carefirst.cardvalue.pbm.emp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface  EmployeeService {
    void deleteEmployee(Long employeeId);
    Employee createNewEmployee(Employee employee);
    List<Employee> getEmployees();
    Optional<Employee> findEmployee(Long employeeId);
}
