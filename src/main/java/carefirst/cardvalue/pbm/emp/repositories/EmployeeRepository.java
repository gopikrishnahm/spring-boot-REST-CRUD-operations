package carefirst.cardvalue.pbm.emp.repositories;

import carefirst.cardvalue.pbm.emp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(int empId);


    void deleteById(int employeeId);

    //void save(Employee employee);

    //void findAll();
}
