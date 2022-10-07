package com.jac.hr.service;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.exception.EmployeeExistException;
import com.jac.hr.exception.EmployeeNotFoundException;

import com.jac.hr.model.EmployeeJoin;
import com.jac.hr.repository.EmployeeJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeJoinService {

    @Autowired
    EmployeeJoinRepository employeeJoinRepository;

    public List<EmployeeJoin> getAllEmployeesJoined() {

        return employeeJoinRepository.getAllEmployeesJoined();
    }

    public List<EmployeeJoin> getAllManagers() {

        return employeeJoinRepository.getAllManagers();
    }

    public EmployeeJoin getManagerByDepName(String depName) {
        try {
            return employeeJoinRepository.getManagerByDepName(depName);
        } catch (DatabaseException exc) {
            return null;
        }
    }

}
