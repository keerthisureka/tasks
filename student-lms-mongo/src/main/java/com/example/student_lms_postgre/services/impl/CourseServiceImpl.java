package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.entity.Course;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.services.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<CourseDto> findAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> dto = new ArrayList<>();
        for (Course c : courses) {
            CourseDto temp = new CourseDto();
            BeanUtils.copyProperties(c, temp);
            dto.add(temp);
        }
        return dto;
    }

    public CourseDto getOne(Long id) {
        Course c = courseRepository.getOne(id);
        CourseDto dto = new CourseDto();
        BeanUtils.copyProperties(c, dto);
        return dto;
    }
}
