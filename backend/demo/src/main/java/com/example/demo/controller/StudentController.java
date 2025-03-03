package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/createStudent", consumes = "application/json")
    public String createStudent(@RequestBody StudentDto s) {
        return studentService.createStudent(s);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getStudentCount/{dept_id}")
    public String getStudentCount(@PathVariable Long dept_id) {
        return studentService.getStudentCount(dept_id);
    }

    @GetMapping("/getStudentsInCity/{city}")
    public List<Student> getStudentsInCity(@PathVariable String city) {
        return studentService.getStudentsInCity(city);
    }
}
