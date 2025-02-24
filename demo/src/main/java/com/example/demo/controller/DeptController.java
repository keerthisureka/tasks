package com.example.demo.controller;

import com.example.demo.dto.DeptDto;
import com.example.demo.entity.Dept;
import com.example.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;


    @PostMapping(value = "/createDept", consumes = "application/json")
    public String createDept(@RequestBody DeptDto d) {
        return deptService.createDept(d);
    }

    @GetMapping("/getAllDepts")
    public List<Dept> getAllDepts() {
        return deptService.getAllDepts();
    }
}
