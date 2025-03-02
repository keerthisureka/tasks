package com.example.student_lms_mongo.services.impl;

import com.example.student_lms_mongo.dto.CourseDto;
import com.example.student_lms_mongo.dto.InstructorDto;
import com.example.student_lms_mongo.dto.StudentDto;
import com.example.student_lms_mongo.entity.Course;
import com.example.student_lms_mongo.entity.Instructor;
import com.example.student_lms_mongo.entity.Organization;
import com.example.student_lms_mongo.entity.Student;
import com.example.student_lms_mongo.exception.InvalidException;
import com.example.student_lms_mongo.exception.NotFoundException;
import com.example.student_lms_mongo.repository.CourseRepository;
import com.example.student_lms_mongo.repository.InstructorRepository;
import com.example.student_lms_mongo.repository.OrganizationRepository;
import com.example.student_lms_mongo.repository.StudentRepository;
import com.example.student_lms_mongo.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            throw new InvalidException("Course fee is missing!");
        }
        Course c = new Course();
        BeanUtils.copyProperties(dto, c);
        c.setOrganizationId(organizationId);
        courseRepository.save(c);
        organization.getCourseIds().add(c.getId());
        organizationRepository.save(organization);
    }

    @Override
    public void deleteCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID; " + courseId));
        if (!c.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        organization.getCourseIds().remove(courseId);
        organizationRepository.save(organization);
        courseRepository.deleteById(courseId);
    }

    @Override
    public int getCountOfStudentsInEachCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!c.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        return c.getStudentIds().size();
    }

    @Override
    public List<InstructorDto> detailsOfInstructorsForEachCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!c.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        List<String> instructorIds = c.getInstructorIds();
        List<InstructorDto> dto = new ArrayList<>();
        for (String id : instructorIds) {
            InstructorDto temp = new InstructorDto();
            Instructor i = instructorRepository.findById(id).get();
            BeanUtils.copyProperties(i, temp);
            dto.add(temp);
        }
        return dto;
    }

    @Override
    public List<StudentDto> detailsOfStudentsForEachCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!c.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        List<String> studentIds = c.getStudentIds();
        List<StudentDto> dto = new ArrayList<>();
        for (String id : studentIds) {
            StudentDto temp = new StudentDto();
            Student s = studentRepository.findById(id).get();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }

    @Override
    public CourseDto detailsOfCourse(String organizationId, String courseId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with given ID: " + courseId + " does not exist!"));
        if (!c.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("The course with the given ID is not offered by the organization!");
        }
        CourseDto dto = new CourseDto();
        BeanUtils.copyProperties(c, dto);
        return dto;
    }
}
