package carefirst.cardvalue.pbm.emp.controller;

import carefirst.cardvalue.pbm.emp.enums.StatusCode;
import carefirst.cardvalue.pbm.emp.exception.EmployeeException;
import carefirst.cardvalue.pbm.emp.model.Employee;
import carefirst.cardvalue.pbm.emp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @PostMapping("/")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        log.info("Creating new Employee.");
        EmployeeException except = null;
        boolean invalidEmail = validateEmpEmail(employee.getEmailAddress());
        boolean invalidLoc =  verifyEmpLocation(employee.getLocation());
        try {
            if( invalidEmail && invalidLoc) {
                Employee _tutorial = employeeService
                        .createNewEmployee(employee);
                return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
            }
            else {

                log.info("Invalid email address entered email is {}  ", employee.getEmailAddress());
                if(!invalidEmail) {
                    except = buildException(StatusCode.INVALID_EMAIL);
                }
                if(!invalidLoc) {
                    except = buildException(StatusCode.INVALID_LOCATION);
                }
                if(!invalidEmail && !invalidLoc) {
                    except = buildException(StatusCode.INVALID_EMAIL_LOC);
                }


                return new ResponseEntity<>(except.getErrorDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.info("Exception occurred while creating a new Employee.");
            except = buildException(StatusCode.ERROR_CREATING_DATA);
            return new ResponseEntity<>(except.getErrorDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getEmployees() {
            log.info("Getting all  employee list.");
            List<Employee> employees = new ArrayList<Employee>();
            try {
                employeeService.getEmployees().forEach(employees::add);
                if (employees.isEmpty()) {
                    return new ResponseEntity<>(StatusCode.NO_DATA_FOUND_ERROR.getDescription(), HttpStatus.OK);
                }
                return new ResponseEntity<>(employees, HttpStatus.OK);
            } catch (Exception e) {
                log.info("Exception occurred while fetching Employees.");
                EmployeeException except = buildException(StatusCode.ERROR_FETCHING_DATA);
                return new ResponseEntity<>(except.getErrorDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/{id}")
    public ResponseEntity <?> findEmployee(@PathVariable("id") String id) {
        log.info("Finding employee with id {}  ", id);
        try {
            Optional<Employee> employeeData = employeeService.findEmployee(Long.valueOf(id));
            if (employeeData.isPresent()) {
                return new ResponseEntity<>(employeeData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(StatusCode.NO_EMPLOYEE_EMPID_FOUND_ERROR.getDescription(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            log.info("Error occurred while finding employee with id {}  ", id);
            EmployeeException except = buildException(StatusCode.ERROR_FETCHING_DATA);
            return new ResponseEntity<>(except.getErrorDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?>  deleteEmployee(@PathVariable("id") String id) {
        try {
            log.info("Deleting employee with id {}  ", id);
            Optional<Employee> employeeData = employeeService.findEmployee(Long.valueOf(id));
          if (employeeData.isPresent()) {
                employeeService.deleteEmployee(Long.valueOf(id));
              return new ResponseEntity<>(StatusCode.DELETE_EMPLOYEE_MSG, HttpStatus.OK);
           } else {
              return new ResponseEntity<>(StatusCode.NO_EMPLOYEE_EMPID_FOUND_ERROR.getDescription(), HttpStatus.OK);
           }
        } catch(Exception e) {
            log.info("Error occurred while deleting employee with id {}  ", id);
            EmployeeException except = buildException(StatusCode.ERROR_FETCHING_DATA);
            return new ResponseEntity<>(except.getErrorDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        log.info("Updating employee with id {}  ", id);
        Optional<Employee> employeeData = employeeService.findEmployee(Long.valueOf(id));
        try {
            if (employeeData.isPresent()) {
                Employee _emp = employeeData.get();
                _emp.setFirstName(employee.getFirstName());
                _emp.setLastName(employee.getLastName());
                _emp.setEmailAddress(employee.getEmailAddress());
                _emp.setPhone(employee.getPhone());
                _emp.setJobTitle(employee.getJobTitle());
                _emp.setDepartment(employee.getDepartment());
                _emp.setLocation(employee.getLocation());
                _emp.setBirthDate(employee.getBirthDate());
                _emp.setStartDate(employee.getStartDate());
                _emp.setReportingManager(employee.getReportingManager());
                return new ResponseEntity<>(employeeService.createNewEmployee(_emp), HttpStatus.OK);
            } else {
                log.info("Error occurred updating employee with id {}  ", id);
                return new ResponseEntity<>(StatusCode.NO_EMPLOYEE_EMPID_FOUND_ERROR.getDescription(), HttpStatus.OK);
            }
        }
        catch(Exception e) {
            log.info("Error occurred while deleting employee with id {}  ", id);
            EmployeeException except = buildException(StatusCode.ERROR_FETCHING_DATA);
            return new ResponseEntity<>(except.getErrorDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private EmployeeException buildException(StatusCode code) {
        return new EmployeeException(code.getCode(), code.getDescription(),
                StatusCode.ERROR_FETCHING_DATA.getDetailDescription());
    }

    private boolean validateEmpEmail(String email) {
        if(email.isEmpty() || !email.contains(".") || !email.contains("@")) {
            return false;
        } else {
            return true;
        }
    }

    private boolean verifyEmpLocation(String location) {
        if(location.equalsIgnoreCase("Washington DC") ||
                location.equalsIgnoreCase("New York") ||
                location.equalsIgnoreCase("Baltimore")) {
            return true;
        } else {
            return false;
        }
    }
}
