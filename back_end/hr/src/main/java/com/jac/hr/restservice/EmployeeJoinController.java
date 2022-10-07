package com.jac.hr.restservice;

import com.jac.hr.model.Employee;
import com.jac.hr.model.EmployeeJoin;
import com.jac.hr.service.EmployeeJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/joined")
public class EmployeeJoinController {

    @Autowired
    private EmployeeJoinService service;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeJoin>> getAllEmployeesJoined(){
        return new ResponseEntity<>(service.getAllEmployeesJoined(), HttpStatus.OK);
    }

    @GetMapping("/managers/")
    public ResponseEntity<List<EmployeeJoin>> getAllManagers(){
        return new ResponseEntity<>(service.getAllManagers(), HttpStatus.OK);
    }

    @GetMapping("/depName/{depName}")
    public ResponseEntity<EmployeeJoin> getManagerByDepName(@PathVariable String depName){
        return new ResponseEntity<>(service.getManagerByDepName(depName), HttpStatus.OK);
    }
}

