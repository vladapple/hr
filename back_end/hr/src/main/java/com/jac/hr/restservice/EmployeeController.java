package com.jac.hr.restservice;

import com.jac.hr.exception.EmployeeExistException;
import com.jac.hr.exception.EmployeeNotFoundException;
import com.jac.hr.model.Employee;
import com.jac.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String lastname){
        return new ResponseEntity<>(service.getEmployeeByName(lastname), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        return new ResponseEntity<>(service.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
    {
        try{
            return new ResponseEntity<>(service.saveEmployee(employee), HttpStatus.CREATED);
        }
        catch (EmployeeExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        try{
            return new ResponseEntity<>(service.updateEmployee(id, employee), HttpStatus.OK);
        }
        catch (EmployeeNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id){
        try{
            service.deleteEmployee(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (EmployeeNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

