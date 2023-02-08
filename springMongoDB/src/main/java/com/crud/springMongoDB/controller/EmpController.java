package com.crud.springMongoDB.controller;


import com.crud.springMongoDB.exception.ResourceNotFoundException;
import com.crud.springMongoDB.model.Employee;
import com.crud.springMongoDB.repository.EmpRepository;
import com.crud.springMongoDB.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/employees/{id}")
    public ResponseEntity <Employee> getEmployeeById (@PathVariable(value= "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = empRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id ::" + employeeId));
                return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        return empRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity <Employee> updateEmployee (@PathVariable(value= "id") Long employeeId,
                                                     @Valid @RequestBody Employee employeeDetails)
        throws ResourceNotFoundException{
        Employee employee = empRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id ::" + employeeId));

       employee.setEmailId(employeeDetails.getEmailId());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        final Employee updatedEmployee = empRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") long employeeId)
       throws ResourceNotFoundException {
        Employee employee = empRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        empRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
