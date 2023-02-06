package com.crud.springMongoDB.controller;


import com.crud.springMongoDB.model.Employee;
import com.crud.springMongoDB.repository.EmpRepository;
import com.crud.springMongoDB.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmpController {

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }

    public ResponseEntity <Employee> getEmployeeById (@PathVariable(value= "id") Long employeeId)
        throws
