package com.example.student_lms_mongo.services.impl;

import com.example.student_lms_mongo.dto.InstructorDto;
import com.example.student_lms_mongo.entity.*;
import com.example.student_lms_mongo.exception.InvalidException;
import com.example.student_lms_mongo.exception.NotFoundException;
import com.example.student_lms_mongo.repository.CourseRepository;
import com.example.student_lms_mongo.repository.InstructorRepository;
import com.example.student_lms_mongo.repository.OrganizationRepository;
import com.example.student_lms_mongo.repository.StudentRepository;
import com.example.student_lms_mongo.services.InstructorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void addInstructor(String organizationId, InstructorDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Instructor name is missing!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Instructor dob is missing!");
        }
        Instructor i = new Instructor();
        BeanUtils.copyProperties(dto, i);
        i.setOrganizationId(organizationId);
        instructorRepository.save(i);
        organization.getInstructorIds().add(i.getId());
        organizationRepository.save(organization);
    }

    @Override
    public void editInstructor(String organizationId, String instructorId, InstructorDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with ID: " + organizationId));
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        if (!i.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("Instructor not registered with the given organization ID!");
        }
        if (dto == null) {
            throw new InvalidException("Enter the instructor details to be updated!");
        }
        if (dto.getName() != null || !dto.getName().isEmpty()) {
            i.setName(dto.getName());
        }
        if (dto.getDob() != null) {
            i.setDob(dto.getDob());
        }
        if (dto.getCourseId() != null) {
            i.setCourseId(dto.getCourseId());
        }
        instructorRepository.save(i);
    }

    @Override
    public void deleteInstructor(String organizationId, String instructorId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with ID: " + organizationId));
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        if (!i.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("Instructor not registered with the given organization ID!");
        }
        instructorRepository.deleteById(instructorId);
        organization.getInstructorIds().remove(instructorId);
        organizationRepository.save(organization);
    }

    @Override
    public void registerForCourse(String instructorId, String courseId) {
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));
        if (i.getCourseId() != null) {
            throw new InvalidException("Instructor already assigned with a course. Cannot assign more than one course!");
        }
        i.setCourseId(courseId);
        instructorRepository.save(i);
        c.getInstructorIds().add(instructorId);
        courseRepository.save(c);
    }

    @Override
    public void deregisterFromCourse(String instructorId) {
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        String courseId = i.getCourseId();
        if (courseId == null) {
            throw new InvalidException("Instructor is not assigned with a course. Cannot deregister from the course!");
        }
        i.setCourseId(null);
        instructorRepository.save(i);
        Course c = courseRepository.findById(courseId).get();
        c.getInstructorIds().remove(instructorId);
        courseRepository.save(c);
    }

    @Override
    public void courseUpdateStatusForStudent(String instructorId, String courseId, String studentId, CourseStatus status) {
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new NotFoundException("Instructor not found with ID: " + instructorId));
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));
        if (!i.getCourseId().equals(courseId)) {
            throw new InvalidException("Instructor is not registered in this course!");
        }
        Optional<StudentCourse> existingCourse = s.getStudentCourses().stream()
                .filter(sc -> sc.getCourseId().equals(courseId)).findFirst();
        if (!existingCourse.isPresent()) {
            throw new InvalidException("Student is not enrolled in this course!");
        }
        existingCourse.get().setStatus(status);
        studentRepository.save(s);
    }
}
