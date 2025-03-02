package com.example.feignOrg.controller;

import com.example.feignOrg.dto.ApiResponse;
import com.example.feignOrg.dto.StudentDto;
import com.example.feignOrg.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/student")
public class feignController {
    @Autowired
    StudentService studentService;

    @GetMapping("/toCallMongo/{choice}/{studentId}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentDetails(@PathVariable boolean choice, @PathVariable String studentId) {
        return studentService.getStudentDetails(choice, studentId);
    }

    @PostMapping("/toCallMongo/{choice}/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> addStudent(@PathVariable boolean choice, @PathVariable String organizationId, @RequestBody StudentDto studentDto) {
        return studentService.addStudent(choice, organizationId, studentDto);
    }
}
