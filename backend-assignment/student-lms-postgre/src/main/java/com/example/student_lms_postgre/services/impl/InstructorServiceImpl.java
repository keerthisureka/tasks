package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.entity.*;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.exception.NotFoundException;
import com.example.student_lms_postgre.repository.*;
import com.example.student_lms_postgre.services.InstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Override
    public void addInstructor(String organizationId, InstructorDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Instructor name is missing!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Instructor DOB is missing!");
        }
        Course course = null;
        if (dto.getCourseId() != null) {
            course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new InvalidException("Invalid course ID!"));
        }
        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(dto, instructor);
        instructor.setOrganization(organization);
        instructor.setCourse(course);
        instructorRepository.save(instructor);
        List<Instructor> instructors = organization.getInstructors();
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
        organization.setInstructors(instructors);
        organizationRepository.save(organization);
    }

    @Override
    @CachePut(value = "instructors", key = "#instructorId")
    public void editInstructor(String organizationId, String instructorId, InstructorDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with ID: " + organizationId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        if (!instructor.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("Instructor not registered with the given organization ID!");
        }
        if (dto == null) {
            throw new InvalidException("Enter the instructor details to be updated!");
        }
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            instructor.setName(dto.getName());
        }
        if (dto.getDob() != null) {
            instructor.setDob(dto.getDob());
        }
        if (dto.getCourseId() != null) {
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new InvalidException("Invalid course ID!"));
            instructor.setCourse(course);
        }
        instructorRepository.save(instructor);
    }

    @Override
    @CacheEvict(value = "instructors", key = "#instructorId")
    public void deleteInstructor(String organizationId, String instructorId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with ID: " + organizationId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        if (!instructor.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("Instructor not registered with the given organization ID!");
        }
        organization.getInstructors().remove(instructor);
        organizationRepository.save(organization);
        instructorRepository.deleteById(instructorId);
    }

    @Override
    public void registerForCourse(String instructorId, String courseId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));
        if (instructor.getCourse() != null) {
            throw new InvalidException("Instructor is already assigned to a course and cannot be assigned to another!");
        }
        instructor.setCourse(course);
        instructorRepository.save(instructor);
        List<Instructor> instructors = course.getInstructors();
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
        course.setInstructors(instructors);
        courseRepository.save(course);
    }

    @Override
    public void deregisterFromCourse(String instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        Course course = instructor.getCourse();
        if (course == null) {
            throw new InvalidException("Instructor is not assigned to any course and cannot be deregistered.");
        }
        instructor.setCourse(null);
        instructorRepository.save(instructor);
        course.getInstructors().remove(instructor);
        courseRepository.save(course);
    }

    @Override
    public void courseUpdateStatusForStudent(String instructorId, String courseId, String studentId, CourseStatus status) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));
        if (!instructor.getCourse().getId().equals(courseId)) {
            throw new InvalidException("Instructor is not assigned to this course!");
        }
        StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(studentId, courseId)
                .orElseThrow(() -> new InvalidException("Student is not enrolled in this course!"));

        studentCourse.setStatus(status);
        studentCourseRepository.save(studentCourse);
    }
}
