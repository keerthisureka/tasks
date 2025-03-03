package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.services.StudentService;
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
