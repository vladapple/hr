package com.jac.hr.service;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.exception.EmployeeExistException;
import com.jac.hr.exception.EmployeeNotFoundException;
import com.jac.hr.model.Employee;
import com.jac.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {

        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeByName(String lastname) {
        try {
            return employeeRepository.getEmployeeByName(lastname);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            return employeeRepository.getEmployeeById(id);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Employee saveEmployee(Employee employee){
        if (getEmployeeByName(employee.getLastname()) == null) {
            return employeeRepository.saveEmployee(employee);
        } else {
            throw new EmployeeExistException("the employee with the same name exists");
        }
    }

    public Employee updateEmployee(int id, Employee employee) {
        Employee fetchedEmployee = getEmployeeById(id);
        if (fetchedEmployee == null) {
            throw new EmployeeNotFoundException("the employee does not exists " + id);
        }
        employeeRepository.updateEmployee(id, employee);

        return employee;
    }

    public void deleteEmployee(int id) {
        Employee fetchedEmployee = getEmployeeById(id);
        if (fetchedEmployee == null) {
            throw new EmployeeNotFoundException("the employee does not exists " + id);
        }
        employeeRepository.deleteEmployeeById(id);
    }


}
