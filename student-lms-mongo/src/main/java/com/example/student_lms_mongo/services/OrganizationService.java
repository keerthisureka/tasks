package com.example.student_lms_mongo.services;

import com.example.student_lms_mongo.dto.CourseDto;
import com.example.student_lms_mongo.dto.InstructorDto;
import com.example.student_lms_mongo.dto.OrganizationDto;
import com.example.student_lms_mongo.dto.StudentDto;
import com.example.student_lms_mongo.entity.CourseStatus;

import java.util.List;

public interface OrganizationService {
    public void createOrganization(OrganizationDto organizationDto);

    // Student
    public void addStudent(String organizationId, StudentDto dto);

    public void editStudent(String organizationId, String studentId, StudentDto dto);

    public void deleteStudent(String organizationId, String studentId);


    // Instructor
    public void addInstructor(String organizationId, InstructorDto dto);

    public void editInstructor(String organizationId, String instructorId, InstructorDto dto);

    public void deleteInstructor(String organizationId, String instructorId);


    // Course
    public void addCourse(String organizationId, CourseDto dto);

    public void deleteCourse(String organizationId, String courseId);

    // Enroll/Withdraw
    public void enrollInCourse(String organizationId, String studentId, String courseId, CourseStatus status);

    public void withdrawFromCourse(String organizationId, String studentId, String courseId);

    public void registerForCourse(String organizationId, String instructorId, String courseId);

    public void deregisterFromCourse(String organizationId, String instructorId);

    // As an organization
    public int countOfStudentsInOrganization(String organizationId);

    public int getCountOfStudentsInEachCourse(String organizationId, String courseId);

    public List<InstructorDto> detailsOfInstructorsForEachCourse(String organizationId, String courseId);

    public int countOfInstructorsInOrganization(String organizationId);

    public List<StudentDto> detailsOfStudentsForEachCourse(String organizationId, String courseId);

    public CourseDto detailsOfCourse(String organizationId, String courseId);

    public List<StudentDto> findStudentByCourseStatus(String organizationId, CourseStatus status);
}
