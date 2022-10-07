package com.jac.hr.restservice;

import com.jac.hr.exception.SeniorityExistException;
import com.jac.hr.exception.SeniorityNotFoundException;
import com.jac.hr.model.Seniority;
import com.jac.hr.service.SeniorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/seniorities")
public class SeniorityController {

    @Autowired
    private SeniorityService service;

    @GetMapping("/")
    public ResponseEntity<List<Seniority>> getAllSeniorities(){
        return new ResponseEntity<>(service.getAllSeniorities(), HttpStatus.OK);
    }

    @GetMapping("/senName/{senName}")
    public ResponseEntity<Seniority> getSeniorityByName(@PathVariable String senName){
        return new ResponseEntity<>(service.getSeniorityByName(senName), HttpStatus.OK);
    }

    @GetMapping("/senId/{senId}")
    public ResponseEntity<Seniority> getSeniorityById(@PathVariable int senId){
        return new ResponseEntity<>(service.getSeniorityById(senId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Seniority> createSeniority(@RequestBody Seniority seniority)
    {
        try{
            return new ResponseEntity<>(service.saveSeniority(seniority), HttpStatus.CREATED);
        }
        catch (SeniorityExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/senId/{senId}")
    public ResponseEntity<Seniority> updateSeniority(@PathVariable int senId, @RequestBody Seniority seniority){
        try{
            return new ResponseEntity<>(service.updateSeniority(senId, seniority), HttpStatus.OK);
        }
        catch (SeniorityNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/senId/{senId}")
    public ResponseEntity deleteSeniority(@PathVariable int senId){
        try{
            service.deleteSeniority(senId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (SeniorityNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

