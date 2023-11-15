package carefirst.cardvalue.pbm.emp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="phone")
    private String phone;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="department")
    private String department;

    @Column(name="location")
    private  String location;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="reporting_manager")
    private String reportingManger;
}
