package com.example.mongo.services;

import com.example.mongo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    public Page<Employee> findAll(Pageable pageable);

    public Employee findOne(String employeeId);

    public Employee insertOrUpdate(Employee employee);

    public void delete(String employeeId);

    public void deleteAll();

    public boolean exists(String employeeId);

    public Long count();

    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    public List<Employee> getEmployees(String lastName);
}
