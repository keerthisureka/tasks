package com.example.student_lms_mongo.controller;

import com.example.student_lms_mongo.dto.ApiResponse;
import com.example.student_lms_mongo.dto.StudentCourseDto;
import com.example.student_lms_mongo.dto.StudentDto;
import com.example.student_lms_mongo.entity.CourseStatus;
import com.example.student_lms_mongo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PutMapping("/enrollInCourse")
    public ResponseEntity<ApiResponse<Void>> enrollInCourse(@RequestParam String studentId, @RequestParam String courseId, @RequestParam CourseStatus status) {
        studentService.enrollInCourse(studentId, courseId, status);
        ApiResponse<Void> response = new ApiResponse<>("Student successfully enrolled!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/withdrawFromCourse")
    public ResponseEntity<ApiResponse<Void>> withdrawFromCourse(@RequestParam String studentId, @RequestParam String courseId) {
        studentService.withdrawFromCourse(studentId, courseId);
        ApiResponse<Void> response = new ApiResponse<>("Student successfully withdrawn from course!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/courseProgress/{studentId}")
    public ResponseEntity<ApiResponse<List<StudentCourseDto>>> courseProgress(@PathVariable String studentId) {
        List<StudentCourseDto> response = studentService.courseProgress(studentId);
        ApiResponse<List<StudentCourseDto>> apiResponse = new ApiResponse<>("Course progress retrieved successfully!", HttpStatus.OK, response);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getStudentDetails/{studentId}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentDetails(@PathVariable String studentId) {
        StudentDto response = studentService.getStudentDetails(studentId);
        ApiResponse<StudentDto> apiResponse = new ApiResponse<>("Student details retrieved successfully!", HttpStatus.OK, response);
        return ResponseEntity.ok(apiResponse);
    }
}
