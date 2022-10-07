package com.jac.hr.restservice;

import com.jac.hr.exception.PositionExistException;
import com.jac.hr.exception.PositionNotFoundException;
import com.jac.hr.model.Position;
import com.jac.hr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping("/")
    public ResponseEntity<List<Position>> getAllPositions(){
        return new ResponseEntity<>(service.getAllPositions(), HttpStatus.OK);
    }

    @GetMapping("/posName/{posName}")
    public ResponseEntity<Position> getPositionByName(@PathVariable String posName){
        return new ResponseEntity<>(service.getPositionByName(posName), HttpStatus.OK);
    }

    @GetMapping("/posId/{posId}")
    public ResponseEntity<Position> getPositionById(@PathVariable int posId){
        return new ResponseEntity<>(service.getPositionById(posId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Position> createPosition(@RequestBody Position position)
    {
        try{
            return new ResponseEntity<>(service.savePosition(position), HttpStatus.CREATED);
        }
        catch (PositionExistException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/posId/{posId}")
    public ResponseEntity<Position> updatePosition(@PathVariable int posId, @RequestBody Position position){
        try{
            return new ResponseEntity<>(service.updatePosition(posId, position), HttpStatus.OK);
        }
        catch (PositionNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/posId/{posId}")
    public ResponseEntity deletePosition(@PathVariable int posId){
        try{
            service.deletePosition(posId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        catch (PositionNotFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

