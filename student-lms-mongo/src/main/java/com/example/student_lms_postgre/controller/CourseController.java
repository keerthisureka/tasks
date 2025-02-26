package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/findAll")
    public List<CourseDto> findAll() {
        return courseService.findAll();
    }

    @GetMapping("/getOne/{id}")
    public CourseDto getOne(@PathVariable Long id) {
        return courseService.getOne(id);
    }
}
