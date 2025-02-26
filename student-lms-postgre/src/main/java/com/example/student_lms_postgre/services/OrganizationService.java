package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.OrganizationDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.CourseStatus;

import java.util.List;

public interface OrganizationService {
    public List<OrganizationDto> findAllOrganizations();

    public void createOrganization(OrganizationDto dto);

    // Student
    public List<StudentDto> getAllStudents(Long id);
    public StudentDto getStudentById(Long id);
    public void addStudent(StudentDto dto);
    public void editStudent(StudentDto dto);
    public void deleteStudent(Long id);

    public void enrollInCourse(Long studentId, Long courseId, CourseStatus status);
    public void withdrawFromCourse(Long studentId, Long courseId);
    public int getCountOfStudentsInEachCourse(Long courseId);

    // Instructor
    public List<InstructorDto> getAllInstructors();
    public InstructorDto getInstructorById(Long id);
    public void addInstructor(InstructorDto dto);
    public void editInstructor(InstructorDto dto);
    public void deleteInstructor(Long id);

    public void registerForCourse(Long instructorId, Long courseId);
    public void deregisterFromCourse(Long instructorId);

    // Course
    public List<CourseDto> getAllCourses();
    public CourseDto getCourseById(Long id);
    public void addCourse(CourseDto dto);
    public void deleteCourse(Long id);

    public List<InstructorDto> getInstructorsForCourse(Long courseId);
}
