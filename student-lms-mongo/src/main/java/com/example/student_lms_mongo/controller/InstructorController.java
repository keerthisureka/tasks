package com.example.student_lms_mongo.controller;

import com.example.student_lms_mongo.dto.ApiResponse;
import com.example.student_lms_mongo.entity.CourseStatus;
import com.example.student_lms_mongo.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    @Autowired
    InstructorService instructorService;

    @PutMapping("/registerForCourse")
    public ResponseEntity<ApiResponse<Void>> registerForCourse(@RequestParam String instructorId, @RequestParam String courseId) {
        instructorService.registerForCourse(instructorId, courseId);
        ApiResponse<Void> response = new ApiResponse<>("Instructor successfully registered for the course!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deregisterFromCourse")
    public ResponseEntity<ApiResponse<Void>> deregisterFromCourse(@RequestParam String instructorId) {
        instructorService.deregisterFromCourse(instructorId);
        ApiResponse<Void> response = new ApiResponse<>("Instructor successfully de-registered from course!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateStudentStatus/{instructorId}")
    public ResponseEntity<ApiResponse<Void>> courseUpdateStatusForStudent(@PathVariable String instructorId, @RequestParam String courseId, @RequestParam String studentId, @RequestParam CourseStatus status) {
        instructorService.courseUpdateStatusForStudent(instructorId, courseId, studentId, status);
        ApiResponse<Void> response = new ApiResponse<>("Course status successfully updated for the given Student ID!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }
}
