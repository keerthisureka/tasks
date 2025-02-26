package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.CourseStatus;
import com.example.student_lms_postgre.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/enrollInCourse")
    public ResponseEntity<Object> enrollInCourse(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam CourseStatus status) {
        studentService.enrollInCourse(studentId, courseId, status);
        return new ResponseEntity<>("Student successfully enrolled!", HttpStatus.OK);
    }

    @DeleteMapping("/withdrawFromCourse")
    public ResponseEntity<Object> withdrawFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        studentService.withdrawFromCourse(studentId, courseId);
        return new ResponseEntity<>("Student successfully withdrawn from course!", HttpStatus.OK);
    }

    @GetMapping("/courseProgress/{id}")
    public ResponseEntity<List<Map<String, Object>>> courseProgress(@PathVariable Long id) {
        List<Map<String, Object>> response = studentService.courseProgress(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
