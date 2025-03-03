package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.*;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.exception.NotFoundException;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.repository.InstructorRepository;
import com.example.student_lms_postgre.repository.OrganizationRepository;
import com.example.student_lms_postgre.repository.StudentRepository;
import com.example.student_lms_postgre.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void addCourse(String organizationId, CourseDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Course name is missing!");
        }
        if (dto.getFee() <= 0.0) {
            throw new InvalidException("Course fee should be above 0.0!");
        }
        Course course = new Course();
        BeanUtils.copyProperties(dto, course);
        course.setOrganization(organization);
        course = courseRepository.save(course);
        List<Course> courseList = organization.getCourses();
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        courseList.add(course);
        organization.setCourses(courseList);
        organizationRepository.save(organization);
    }

    @Override
    public void deleteCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));
        if (!course.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        List<Course> updatedCourses = organization.getCourses()
                .stream()
                .filter(c -> !c.getId().equals(courseId))
                .collect(Collectors.toList());
        organization.setCourses(updatedCourses);
        organizationRepository.save(organization);
        courseRepository.deleteById(courseId);
    }

    @Override
    public int getCountOfStudentsInEachCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!course.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        return course.getStudentCourses().size();
    }

    @Override
    public List<InstructorDto> detailsOfInstructorsForEachCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!course.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        return course.getInstructors().stream().map(instructor -> {
            InstructorDto dto = new InstructorDto();
            BeanUtils.copyProperties(instructor, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> detailsOfStudentsForEachCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!course.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        return course.getStudentCourses().stream().map(studentCourse -> {
            StudentDto dto = new StudentDto();
            BeanUtils.copyProperties(studentCourse.getStudent(), dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CourseDto detailsOfCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!course.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        CourseDto dto = new CourseDto();
        BeanUtils.copyProperties(course, dto);
        return dto;
    }
}
