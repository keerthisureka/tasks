package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/findAll")
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/getOne/{id}")
    public StudentDto getOne(@PathVariable Long id) {
        return studentService.getOne(id);
    }
}
