package com.example.postgre.controller;

import com.example.postgre.dto.StudentDto;
import com.example.postgre.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/getStudentById/{id}")
    public StudentDto getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }
}
