package com.academy.EmployeeManagementSystem.controller;

import com.academy.EmployeeManagementSystem.conf.ConfirmationForm;
import com.academy.EmployeeManagementSystem.model.Employee;
import com.academy.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public String getEmployee(Model model) {
        List<Employee> employees = service.findAll();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("confirmationForm", new ConfirmationForm());
        return "employee";
    }

    @PostMapping
    public ResponseEntity<String> newEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            model.addAttribute("employee", new Employee());
            service.saveTo(employee);
            return ResponseEntity.ok("Employee added successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateEmployee(@ModelAttribute Employee employee) {
        try {
            service.findById(employee.getId());
            service.saveTo(employee);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeEmployee(@RequestParam Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/remove/all")
    public ResponseEntity<String> removeAll(@ModelAttribute ConfirmationForm confirmationForm) {
        String conf = confirmationForm.getConfirmation();
        if ("Yes".equalsIgnoreCase(conf)) {
            service.deleteAll();
            return ResponseEntity.ok("All employees deleted successfully");
        } else {
            return new ResponseEntity<>("Confirmation failed", HttpStatus.BAD_REQUEST);
        }
    }
}
