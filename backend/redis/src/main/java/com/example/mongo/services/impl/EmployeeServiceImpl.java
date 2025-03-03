package com.example.mongo.services.impl;

import com.example.mongo.entity.Employee;
import com.example.mongo.repository.EmployeeRepository;
import com.example.mongo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findOne(String employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public Employee insertOrUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    public Long count() {
        return employeeRepository.count();
    }
}
