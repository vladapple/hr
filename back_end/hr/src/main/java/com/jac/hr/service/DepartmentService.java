package com.jac.hr.service;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.exception.DepartmentExistException;
import com.jac.hr.exception.DepartmentNotFoundException;
import com.jac.hr.model.Department;
import com.jac.hr.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {

        return departmentRepository.getAllDepartments();
    }

    public Department getDepartmentByName(String depName) {
        try {
            return departmentRepository.getDepartmentByName(depName);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Department getDepartmentById(int depId) {
        try {
            return departmentRepository.getDepartmentById(depId);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Department saveDepartment(Department department){
        if (getDepartmentByName(department.getDepName()) == null) {
            return departmentRepository.saveDepartment(department);
        } else {
            throw new DepartmentExistException("the department with the same name exists");
        }
    }

    public Department updateDepartment(int depId, Department department) {
        Department fetchedDepartment = getDepartmentById(depId);
        if (fetchedDepartment == null) {
            throw new DepartmentNotFoundException("the department does not exists " + depId);
        }
        departmentRepository.updateDepartment(depId, department);

        return department;
    }

    public void deleteDepartment(int depId) {
        Department fetchedDepartment = getDepartmentById(depId);
        if (fetchedDepartment == null) {
            throw new DepartmentNotFoundException("the department does not exists " + depId);
        }
        departmentRepository.deleteDepartmentById(depId);
    }


}
