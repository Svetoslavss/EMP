package com.academy.EmployeeManagementSystem.service;

import com.academy.EmployeeManagementSystem.model.Employee;
import com.academy.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee saveTo(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Long id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid id provider.");
        }
    }

    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID " + id));
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}