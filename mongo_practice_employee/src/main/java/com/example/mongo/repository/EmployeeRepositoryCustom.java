package com.example.mongo.repository;

import com.example.mongo.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<Employee> getEmployees(String lastName);
}
