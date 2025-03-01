package com.example.student_lms_mongo.services.impl;

import com.example.student_lms_mongo.dto.CourseDto;
import com.example.student_lms_mongo.entity.Course;
import com.example.student_lms_mongo.entity.Organization;
import com.example.student_lms_mongo.exception.InvalidException;
import com.example.student_lms_mongo.exception.NotFoundException;
import com.example.student_lms_mongo.repository.CourseRepository;
import com.example.student_lms_mongo.repository.OrganizationRepository;
import com.example.student_lms_mongo.services.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void addCourse(CourseDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Course name is missing!");
        }
        if (dto.getFee() <= 0.0) {
            throw new InvalidException("Course fee is missing!");
        }
        if (dto.getOrganizationId() == null) {
            throw new InvalidException("Course's organization_id is missing!");
        }
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + dto.getOrganizationId()));
        Course c = new Course();
        BeanUtils.copyProperties(dto, c);
        courseRepository.save(c);
        organization.getCourseIds().add(c.getId());
        organizationRepository.save(organization);
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public int getCountOfStudentsInEachCourse(String courseId) {
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidException("Course with given ID: " + courseId + " does not exist!"));
        return c.getStudentIds().size();
    }
}
