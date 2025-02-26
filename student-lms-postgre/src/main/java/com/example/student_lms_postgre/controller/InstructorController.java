package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @GetMapping("/findAll")
    public List<InstructorDto> findAll() {
        return instructorService.findAll();
    }

    @GetMapping("getOne/{id}")
    public InstructorDto getOne(@PathVariable Long id) {
        return instructorService.getOne(id);
    }

    @GetMapping("/registerForCourse")
    public ResponseEntity<Object> registerForCourse(@RequestParam Long instructorId, @RequestParam Long courseId) {
        instructorService.registerForCourse(instructorId, courseId);
        return new ResponseEntity<>("Instructor successfully registered for the course!", HttpStatus.OK);
    }

    @DeleteMapping("/deregisterFromCourse")
    public ResponseEntity<Object> deregisterFromCourse(@RequestParam Long instructorId) {
        instructorService.deregisterFromCourse(instructorId);
        return new ResponseEntity<>("Instructor successfully de-registered from course!", HttpStatus.OK);
    }
}
