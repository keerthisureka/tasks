package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.OrganizationDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.*;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.exception.NotFoundException;
import com.example.student_lms_postgre.repository.*;
import com.example.student_lms_postgre.services.CourseService;
import com.example.student_lms_postgre.services.InstructorService;
import com.example.student_lms_postgre.services.OrganizationService;
import com.example.student_lms_postgre.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    StudentService studentService;
    @Autowired
    InstructorService instructorService;
    @Autowired
    CourseService courseService;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Override
    public void createOrganization(OrganizationDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Organization name is missing!");
        }
        Organization o = new Organization();
        BeanUtils.copyProperties(dto, o);
        organizationRepository.save(o);
    }

    // Student
    @Override
    public void addStudent(String organizationId, StudentDto dto) {
        studentService.addStudent(organizationId, dto);
    }

    @Override
    public void editStudent(String organizationId, String studentId, StudentDto dto) {
        studentService.editStudent(organizationId, studentId, dto);
    }

    @Override
    public void deleteStudent(String organizationId, String studentId) {
        studentService.deleteStudent(organizationId, studentId);
    }

    // Instructor
    @Override
    public void addInstructor(String organizationId, InstructorDto dto) {
        instructorService.addInstructor(organizationId, dto);
    }

    @Override
    public void editInstructor(String organizationId, String instructorId, InstructorDto dto) {
        instructorService.editInstructor(organizationId, instructorId, dto);
    }

    @Override
    public void deleteInstructor(String organizationId, String instructorId) {
        instructorService.deleteInstructor(organizationId, instructorId);
    }

    // Course
    @Override
    public void addCourse(String organizationId, CourseDto dto) {
        courseService.addCourse(organizationId, dto);
    }

    @Override
    public void deleteCourse(String organizationId, String courseId) {
        courseService.deleteCourse(organizationId, courseId);
    }

    // Enroll/Withdraw
    // Student
    @Override
    public void enrollInCourse(String organizationId, String studentId, String courseId, CourseStatus status) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (!organizationRepository.findStudentIdsByOrganization(organizationId).contains(studentId)) {
            throw new InvalidException("Student not registered with the given organization ID!");
        }
        studentService.enrollInCourse(studentId, courseId, status);
    }

    @Override
    public void withdrawFromCourse(String organizationId, String studentId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (!organizationRepository.findStudentIdsByOrganization(organizationId).contains(studentId)) {
            throw new InvalidException("Student not registered with the given organization ID!");
        }
        studentService.withdrawFromCourse(studentId, courseId);
    }

    // Instructor
    @Override
    public void registerForCourse(String organizationId, String instructorId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (!organizationRepository.findInstructorIdsByOrganization(organizationId).contains(instructorId)) {
            throw new InvalidException("Instructor not registered with the given organization ID!");
        }
        instructorService.registerForCourse(instructorId, courseId);
    }

    @Override
    public void deregisterFromCourse(String organizationId, String instructorId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (!organizationRepository.findInstructorIdsByOrganization(organizationId).contains(instructorId)) {
            throw new InvalidException("Instructor not registered with the given organization ID!");
        }
        instructorService.deregisterFromCourse(instructorId);
    }

    // As an organization
    @Override
    public int countOfStudentsInOrganization(String organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        return organization.getStudents().size();
    }

    @Override
    public int getCountOfStudentsInEachCourse(String organizationId, String courseId) {
        return courseService.getCountOfStudentsInEachCourse(organizationId, courseId);
    }

    @Override
    public List<InstructorDto> detailsOfInstructorsForEachCourse(String organizationId, String courseId) {
        return courseService.detailsOfInstructorsForEachCourse(organizationId, courseId);
    }

    @Override
    public int countOfInstructorsInOrganization(String organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        return organization.getInstructors().size();
    }

    @Override
    public List<StudentDto> detailsOfStudentsForEachCourse(String organizationId, String courseId) {
        return courseService.detailsOfStudentsForEachCourse(organizationId, courseId);
    }

    @Override
    public CourseDto detailsOfCourse(String organizationId, String courseId) {
        return courseService.detailsOfCourse(organizationId, courseId);
    }

    @Override
    public List<StudentDto> findStudentByCourseStatus(String organizationId, String courseId, CourseStatus status) {
        return studentService.findStudentByCourseStatus(organizationId, courseId, status);
    }
}
