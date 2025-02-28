package com.example.mongo.services.impl;

import com.example.mongo.entity.Employee;
import com.example.mongo.repository.EmployeeRepository;
import com.example.mongo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Cacheable(key = "#employeeId", cacheNames = "employee")
    public Employee findOne(String employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    @CachePut(cacheNames = "employee", key = "#employee.employeeId")
    //@CacheEvict(cacheNames = "employee", key = "#employee.employeeId")
    public Employee insertOrUpdate(Employee employee) {
        if (employee.getEmployeeId() != null) {
            employeeRepository.save(employee);
        }
        else {
            employeeRepository.save(employee);
        }
        return employeeRepository.save(employee);
    }

    public void delete(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    public boolean exists(String employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    public Long count() {
        return employeeRepository.count();
    }

    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Employee> getEmployees(String lastName) {
        return employeeRepository.getEmployees(lastName);
    }
}
