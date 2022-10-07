package com.jac.hr.restservice;

import com.jac.hr.exception.DepartmentExistException;
import com.jac.hr.exception.DepartmentNotFoundException;
import com.jac.hr.model.Department;
import com.jac.hr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return new ResponseEntity<>(service.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/depName/{depName}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String depName){
        return new ResponseEntity<>(service.getDepartmentByName(depName), HttpStatus.OK);
    }

    @GetMapping("/depId/{depId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int depId){
        return new ResponseEntity<>(service.getDepartmentById(depId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department)
    {
        try{
            return new ResponseEntity<>(service.saveDepartment(department), HttpStatus.CREATED);
        }
        catch (DepartmentExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/depId/{depId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int depId, @RequestBody Department department){
        try{
            return new ResponseEntity<>(service.updateDepartment(depId, department), HttpStatus.OK);
        }
        catch (DepartmentNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/depId/{depId}")
    public ResponseEntity deleteDepartment(@PathVariable int depId){
        try{
            service.deleteDepartment(depId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (DepartmentNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

