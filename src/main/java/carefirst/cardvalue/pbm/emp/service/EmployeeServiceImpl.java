package carefirst.cardvalue.pbm.emp.service;

import carefirst.cardvalue.pbm.emp.model.Employee;
import carefirst.cardvalue.pbm.emp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
    public Employee createNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    public Optional<Employee> findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }
}
