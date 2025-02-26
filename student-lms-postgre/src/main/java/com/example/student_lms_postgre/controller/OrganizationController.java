package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.OrganizationDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @GetMapping("/findAllOrganizations")
    public ResponseEntity<List<OrganizationDto>> findAll() {
        return new ResponseEntity<>(organizationService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createOrganization")
    public ResponseEntity<Object> createOrganization(OrganizationDto dto) {
        organizationService.createOrganization(dto);
        return new ResponseEntity<>("Organization created successfully!", HttpStatus.CREATED);
    }

    // Student
    @GetMapping("/getAllStudents")
    public ResponseEntity<Map<String, Object>> getAllStudents() {
        List<StudentDto> students = organizationService.getAllStudents();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All students retrieved successfully!");
        response.put("data", students); // The actual list of student details

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
        StudentDto studentDto = organizationService.getStudentById(id);
        return new ResponseEntity<>("Student retrieved successfully: \n" + studentDto, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody StudentDto dto) {
        organizationService.addStudent(dto);
        return new ResponseEntity<>("Student details added successfully!", HttpStatus.CREATED);
    }

    @PutMapping ("/editStudent")
    public ResponseEntity<Object> editStudent(@RequestBody StudentDto dto) {
        organizationService.editStudent(dto);
        return new ResponseEntity<>("Student details edited successfully!", HttpStatus.OK);
    }

    @DeleteMapping ("/deleteStudent/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        organizationService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK);
    }

    // Instructor
    @GetMapping("/getAllInstructors")
    public ResponseEntity<Map<String, Object>> getAllInstructors() {
        List<InstructorDto> instructors = organizationService.getAllInstructors();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All instructors retrieved successfully!");
        response.put("data", instructors); // The actual list of instructor details

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getInstructorById/{id}")
    public ResponseEntity<Object> getInstructorById(@PathVariable Long id) {
        InstructorDto instructorDto = organizationService.getInstructorById(id);
        return new ResponseEntity<>("Instructor retrieved successfully: \n" + instructorDto, HttpStatus.OK);
    }

    @PostMapping("/addInstructor")
    public ResponseEntity<Object> addInstructor(@RequestBody InstructorDto dto) {
        organizationService.addInstructor(dto);
        return new ResponseEntity<>("Instructor details added successfully!", HttpStatus.CREATED);
    }

    @PutMapping ("/editInstructor")
    public ResponseEntity<Object> editInstructor(@RequestBody InstructorDto dto) {
        organizationService.editInstructor(dto);
        return new ResponseEntity<>("Instructor details edited successfully!", HttpStatus.OK);
    }

    @DeleteMapping ("/deleteInstructor/{id}")
    public ResponseEntity<Object> deleteInstructor(@PathVariable Long id) {
        organizationService.deleteInstructor(id);
        return new ResponseEntity<>("Instructor deleted successfully!", HttpStatus.OK);
    }

    // Course
    @GetMapping("/getAllCourses")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        List<CourseDto> courses = organizationService.getAllCourses();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All courses retrieved successfully!");
        response.put("data", courses); // The actual list of course details

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {
        CourseDto courseDto = organizationService.getCourseById(id);
        return new ResponseEntity<>("Course retrieved successfully: \n" + courseDto, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseDto courseDto) {
        organizationService.addCourse(courseDto);  // This calls the service method
        return new ResponseEntity<>("Course added successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping ("/deleteCourse/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        organizationService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully!", HttpStatus.OK);
    }
}
