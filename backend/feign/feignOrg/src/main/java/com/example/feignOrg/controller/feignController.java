package com.example.feignOrg.controller;

import com.example.feignOrg.dto.StudentDto;
import com.example.feignOrg.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/student")
public class feignController {
    @Autowired
    StudentService studentService;

    @GetMapping("/toCallMongo/{choice}/{id}")
    public StudentDto toCallMongo(@PathVariable boolean choice, @PathVariable String id) {
        return studentService.toCallMongo(choice, id);
    }
}
