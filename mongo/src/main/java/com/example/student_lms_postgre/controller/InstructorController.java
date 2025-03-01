package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
