package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.OrganizationDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.Course;
import com.example.student_lms_postgre.entity.CourseStatus;
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
    public ResponseEntity<List<OrganizationDto>> findAllOrganizations() {
        return new ResponseEntity<>(organizationService.findAllOrganizations(), HttpStatus.OK);
    }

    @PostMapping("/createOrganization")
    public ResponseEntity<Object> createOrganization(OrganizationDto dto) {
        organizationService.createOrganization(dto);
        return new ResponseEntity<>("Organization created successfully!", HttpStatus.CREATED);
    }

    // Student
    @GetMapping("/getAllStudents/{id}")
    public ResponseEntity<Map<String, Object>> getAllStudents(@PathVariable Long organizationId) {
        List<StudentDto> students = organizationService.getAllStudents(organizationId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All students in the organization with id: " + organizationId + " retrieved successfully!");
        response.put("data", students); // The actual list of student details

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable Long studentId) {
        StudentDto studentDto = organizationService.getStudentById(studentId);
        return new ResponseEntity<>("Student retrieved successfully: \n" + studentDto, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> addStudent(@RequestBody StudentDto dto) {
        organizationService.addStudent(dto);
        return new ResponseEntity<>("Student details added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/editStudent")
    public ResponseEntity<Object> editStudent(@RequestBody StudentDto dto) {
        organizationService.editStudent(dto);
        return new ResponseEntity<>("Student details edited successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        organizationService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/enrollInCourse")
    public ResponseEntity<Object> enrollInCourse(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam CourseStatus status) {
        organizationService.enrollInCourse(studentId, courseId, status);
        return new ResponseEntity<>("Student successfully enrolled!", HttpStatus.OK);
    }

    @DeleteMapping("/withdrawFromCourse")
    public ResponseEntity<Object> withdrawFromCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        organizationService.withdrawFromCourse(studentId, courseId);
        return new ResponseEntity<>("Student successfully withdrawn from course!", HttpStatus.OK);
    }

    @GetMapping("/getCountOfStudents/{id}")
    public ResponseEntity<Integer> getCountOfStudents(@PathVariable Long organizationId) {
        List<StudentDto> students = organizationService.getAllStudents(organizationId);
        return new ResponseEntity<>(students.size(), HttpStatus.OK);
    }

    @GetMapping("/getCountOfStudentsInEachCourse/{id}")
    public ResponseEntity<Integer> getCountOfStudentsInEachCourse(@PathVariable Long courseId) {
        int count = organizationService.getCountOfStudentsInEachCourse(courseId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Instructor
    @GetMapping("/getAllInstructors")
    public ResponseEntity<Map<String, Object>> getAllInstructors() {
        List<InstructorDto> instructors = organizationService.getAllInstructors();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All instructors retrieved successfully!");
        response.put("data", instructors);

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

    @PutMapping("/editInstructor")
    public ResponseEntity<Object> editInstructor(@RequestBody InstructorDto dto) {
        organizationService.editInstructor(dto);
        return new ResponseEntity<>("Instructor details edited successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/deleteInstructor/{id}")
    public ResponseEntity<Object> deleteInstructor(@PathVariable Long id) {
        organizationService.deleteInstructor(id);
        return new ResponseEntity<>("Instructor deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/registerForCourse")
    public ResponseEntity<Object> registerForCourse(@RequestParam Long instructorId, @RequestParam Long courseId) {
        organizationService.registerForCourse(instructorId, courseId);
        return new ResponseEntity<>("Instructor successfully registered for the course!", HttpStatus.OK);
    }

    @DeleteMapping("/deregisterFromCourse")
    public ResponseEntity<Object> deregisterFromCourse(@RequestParam Long instructorId) {
        organizationService.deregisterFromCourse(instructorId);
        return new ResponseEntity<>("Instructor successfully de-registered from course!", HttpStatus.OK);
    }

    @GetMapping("/countOfInstructors/{id}")
    public ResponseEntity<Object> countOfInstructors(@PathVariable Long organizationId) {
        Long cnt = organizationService.countOfInstructors(organizationId);
        return new ResponseEntity<>("The count of instructors in the organization with id: " + organizationId + " is " + cnt, HttpStatus.OK);
    }

    // Course
    @GetMapping("/getAllCourses")
    public ResponseEntity<Map<String, Object>> getAllCourses() {
        List<CourseDto> courses = organizationService.getAllCourses();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "All courses retrieved successfully!");
        response.put("data", courses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Long id) {
        CourseDto courseDto = organizationService.getCourseById(id);
        return new ResponseEntity<>("Course retrieved successfully: \n" + courseDto, HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Object> addCourse(@RequestBody CourseDto courseDto) {
        organizationService.addCourse(courseDto);
        return new ResponseEntity<>("Course added successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id) {
        organizationService.deleteCourse(id);
        return new ResponseEntity<>("Course deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/getInstructorsForCourse/{id}")
    public ResponseEntity<Object> getInstructorsForCourse(@PathVariable Long id) {
        List<InstructorDto> instructorDto = organizationService.getInstructorsForCourse(id);
        return new ResponseEntity<>("Here are the instructors for the course: \n" + instructorDto, HttpStatus.OK);
    }

    // 6 (e, f)
    @GetMapping("/students/{courseId}")
    public ResponseEntity<Object> students(@PathVariable Long courseId) {
        List<StudentDto> studentDto = organizationService.students(courseId);
        return new ResponseEntity<>("Here are the students enrolled for this course: \n" + studentDto, HttpStatus.OK);
    }

    @GetMapping("/instructors/{courseId}")
    public ResponseEntity<Object> instructors(@PathVariable Long courseId) {
        List<InstructorDto> instructorDto = organizationService.instructors(courseId);
        return new ResponseEntity<>("Here are the instructors registered for this course: \n" + instructorDto, HttpStatus.OK);
    }
}
