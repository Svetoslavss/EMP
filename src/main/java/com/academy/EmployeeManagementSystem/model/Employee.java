package com.academy.EmployeeManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private String department;
    private String gender;
    private Long employeePhone;
    private String employeeEmail;
    private double salary;

}


