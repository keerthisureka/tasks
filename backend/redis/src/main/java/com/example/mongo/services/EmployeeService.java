package com.example.mongo.services;

import com.example.mongo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    public Iterable<Employee> findAll();

    public Employee findOne(String employeeId);

    public Employee insertOrUpdate(Employee employee);

    public void delete(String employeeId);

    public void deleteAll();

    public Long count();
}
