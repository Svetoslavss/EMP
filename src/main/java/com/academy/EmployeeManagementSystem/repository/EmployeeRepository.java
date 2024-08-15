package com.academy.EmployeeManagementSystem.repository;

import com.academy.EmployeeManagementSystem.model.Employee;
import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Enabled
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartment(String department);
}
