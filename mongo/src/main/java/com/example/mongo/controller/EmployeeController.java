package com.example.mongo.controller;

import com.example.mongo.dto.EmployeeDto;
import com.example.mongo.entity.Employee;
import com.example.mongo.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

//    @GetMapping("/getAll")
//    public List<EmployeeDto> getAll() {
//        List<Employee> emps = employeeService.findAll();
//        List<EmployeeDto> dto = new ArrayList<>();
//        for (Employee e : emps) {
//            EmployeeDto empDto = new EmployeeDto();
//            BeanUtils.copyProperties(e, empDto);
//            dto.add(empDto);
//        }
//        return dto;
//    }

    @GetMapping("/getAll")
    public Page<EmployeeDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").descending());
        Page<Employee> emps = employeeService.findAll(pageable);
        List<EmployeeDto> dto = new ArrayList<>();
        for (Employee e : emps) {
            EmployeeDto empDto = new EmployeeDto();
            BeanUtils.copyProperties(e, empDto);
            dto.add(empDto);
        }
        return new PageImpl<>(dto, pageable, emps.getTotalElements());
    }

    @GetMapping("/getOne/{id}")
    public EmployeeDto getOne(@PathVariable String id) {
        Employee emp = employeeService.findOne(id);
        EmployeeDto empDto = new EmployeeDto();
        BeanUtils.copyProperties(emp, empDto);
        return empDto;
    }

    @PostMapping("/insertOrUpdate")
    public void insertOrUpdate(@RequestBody EmployeeDto empDto) {
        Employee emp = new Employee();
        BeanUtils.copyProperties(empDto, emp);
        employeeService.insertOrUpdate(emp);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        employeeService.delete(id);
    }

    @PostMapping("/deleteAll")
    public void deleteAll() {
        employeeService.deleteAll();
    }

    @GetMapping("/getCount")
    public Long getCount() {
        return employeeService.count();
    }

    @GetMapping("/findByFirstNameAndLastName")
    public List<EmployeeDto> findByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        List<Employee> empList = employeeService.findByFirstNameAndLastName(firstName, lastName);
        List<EmployeeDto> dto = new ArrayList<>();
        for(Employee e : empList) {
            EmployeeDto empDto = new EmployeeDto();
            BeanUtils.copyProperties(e, empDto);
            dto.add(empDto);
        }
        return dto;
    }

    @GetMapping("/getEmployees")
    public List<EmployeeDto> getEmployees(@RequestParam String lastName) {
        List<Employee> empList = employeeService.getEmployees(lastName);
        List<EmployeeDto> dto = new ArrayList<>();
        for(Employee e : empList) {
            EmployeeDto empDto = new EmployeeDto();
            BeanUtils.copyProperties(e, empDto);
            dto.add(empDto);
        }
        return dto;
    }
}
