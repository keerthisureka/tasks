package com.example.student_lms_mongo.controller;

import com.example.student_lms_mongo.dto.*;
import com.example.student_lms_mongo.entity.CourseStatus;
import com.example.student_lms_mongo.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PostMapping("/createOrganization")
    public ResponseEntity<ApiResponse<Void>> createOrganization(@RequestBody OrganizationDto dto) {
        organizationService.createOrganization(dto);
        ApiResponse<Void> response = new ApiResponse<>("Organization created successfully!", HttpStatus.CREATED, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Student
    @PostMapping("/addStudent/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> addStudent(@PathVariable String organizationId, @RequestBody StudentDto studentDto) {
        organizationService.addStudent(organizationId, studentDto);
        ApiResponse<Void> response = new ApiResponse<>("Student details added successfully!", HttpStatus.CREATED, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/editStudent/{organizationId}/{studentId}")
    public ResponseEntity<ApiResponse<Void>> editStudent(@PathVariable String organizationId, @PathVariable String studentId, @RequestBody StudentDto studentDto) {
        organizationService.editStudent(organizationId, studentId, studentDto);
        ApiResponse<Void> response = new ApiResponse<>("Student details edited successfully!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteStudent/{organizationId}/{studentId}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String organizationId, @PathVariable String studentId) {
        organizationService.deleteStudent(organizationId, studentId);
        ApiResponse<Void> response = new ApiResponse<>("Student deleted successfully!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    // Instructor
    @PostMapping("/addInstructor/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> addInstructor(@PathVariable String organizationId, @RequestBody InstructorDto instructorDto) {
        organizationService.addInstructor(organizationId, instructorDto);
        ApiResponse<Void> response = new ApiResponse<>("Instructor details added successfully!", HttpStatus.CREATED, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/editInstructor/{organizationId}/{instructorId}")
    public ResponseEntity<ApiResponse<Void>> editInstructor(@PathVariable String organizationId, @PathVariable String instructorId, @RequestBody InstructorDto instructorDto) {
        organizationService.editInstructor(organizationId, instructorId, instructorDto);
        ApiResponse<Void> response = new ApiResponse<>("Instructor details edited successfully!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteInstructor/{organizationId}/{instructorId}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(@PathVariable String organizationId, @PathVariable String instructorId) {
        organizationService.deleteInstructor(organizationId, instructorId);
        ApiResponse<Void> response = new ApiResponse<>("Instructor deleted successfully!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    // Course
    @PostMapping("/addCourse/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> addCourse(@PathVariable String organizationId, @RequestBody CourseDto courseDto) {
        organizationService.addCourse(organizationId, courseDto);
        ApiResponse<Void> response = new ApiResponse<>("Course added successfully!", HttpStatus.CREATED, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/deleteCourse/{organizationId}/{courseId}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable String organizationId, @PathVariable String courseId) {
        organizationService.deleteCourse(organizationId, courseId);
        ApiResponse<Void> response = new ApiResponse<>("Course deleted successfully!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    // Enroll/Withdraw
    @GetMapping("/enrollInCourse/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> enrollInCourse(@PathVariable String organizationId, @RequestParam String studentId, @RequestParam String courseId, @RequestParam CourseStatus status) {
        organizationService.enrollInCourse(organizationId, studentId, courseId, status);
        ApiResponse<Void> response = new ApiResponse<>("Student successfully enrolled!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/withdrawFromCourse/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> withdrawFromCourse(@PathVariable String organizationId, @RequestParam String studentId, @RequestParam String courseId) {
        organizationService.withdrawFromCourse(organizationId, studentId, courseId);
        ApiResponse<Void> response = new ApiResponse<>("Student successfully withdrawn from course!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/registerForCourse/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> registerForCourse(@PathVariable String organizationId, @RequestParam String instructorId, @RequestParam String courseId) {
        organizationService.registerForCourse(organizationId, instructorId, courseId);
        ApiResponse<Void> response = new ApiResponse<>("Instructor successfully registered for the course!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deregisterFromCourse/{organizationId}")
    public ResponseEntity<ApiResponse<Void>> deregisterFromCourse(@PathVariable String organizationId, @RequestParam String instructorId) {
        organizationService.deregisterFromCourse(organizationId, instructorId);
        ApiResponse<Void> response = new ApiResponse<>("Instructor successfully de-registered from course!", HttpStatus.OK, null);
        return ResponseEntity.ok(response);
    }

    // As an organization
    @GetMapping("/countOfStudentsInOrganization/{organizationId}")
    public ResponseEntity<ApiResponse<Integer>> countOfStudentsInOrganization(@PathVariable String organizationId) {
        int count = organizationService.countOfStudentsInOrganization(organizationId);
        ApiResponse<Integer> response = new ApiResponse<>("Here are the students enrolled for this course:", HttpStatus.OK, count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCountOfStudentsInEachCourse/{organizationId}/{courseId}")
    public ResponseEntity<ApiResponse<Integer>> getCountOfStudentsInEachCourse(@PathVariable String organizationId, @PathVariable String courseId) {
        int count = organizationService.getCountOfStudentsInEachCourse(organizationId, courseId);
        ApiResponse<Integer> response = new ApiResponse<>("Student count for the course", HttpStatus.OK, count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detailsOfInstructorsForEachCourse/{organizationId}/{courseId}")
    public ResponseEntity<ApiResponse<List<InstructorDto>>> detailsOfInstructorsForEachCourse(@PathVariable String organizationId, @PathVariable String courseId) {
        List<InstructorDto> instructorDtos = organizationService.detailsOfInstructorsForEachCourse(organizationId, courseId);
        ApiResponse<List<InstructorDto>> response = new ApiResponse<>("Here are the instructors registered for this course: ", HttpStatus.OK, instructorDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/countOfInstructorsInOrganization/{organizationId}")
    public ResponseEntity<ApiResponse<Integer>> countOfInstructorsInOrganization(@PathVariable String organizationId) {
        int count = organizationService.countOfInstructorsInOrganization(organizationId);
        ApiResponse<Integer> response = new ApiResponse<>("The count of instructors in the organization with ID " + organizationId + " is:", HttpStatus.OK, count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detailsOfStudentsForEachCourse/{organizationId}/{courseId}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> detailsOfStudentsForEachCourse(@PathVariable String organizationId, @PathVariable String courseId) {
        List<StudentDto> studentDtos = organizationService.detailsOfStudentsForEachCourse(organizationId, courseId);
        ApiResponse<List<StudentDto>> response = new ApiResponse<>("Here are the students enrolled for this course: ", HttpStatus.OK, studentDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detailsOfCourse/{organizationId}/{courseId}")
    public ResponseEntity<ApiResponse<CourseDto>> detailsOfCourse(@PathVariable String organizationId, @PathVariable String courseId) {
        CourseDto courseDto = organizationService.detailsOfCourse(organizationId, courseId);
        ApiResponse<CourseDto> response = new ApiResponse<>("Here are the course details for this course: ", HttpStatus.OK, courseDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findStudentByCourseStatus/{organizationId}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findStudentByCourseStatus(@PathVariable String organizationId, @RequestParam CourseStatus status) {
        List<StudentDto> studentDtos = organizationService.findStudentByCourseStatus(organizationId, status);
        ApiResponse<List<StudentDto>> response = new ApiResponse<>("Here are the students for the selected course status:", HttpStatus.OK, studentDtos);
        return ResponseEntity.ok(response);
    }
}
