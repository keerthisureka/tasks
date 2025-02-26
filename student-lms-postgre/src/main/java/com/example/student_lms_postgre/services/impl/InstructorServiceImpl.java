package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.entity.Course;
import com.example.student_lms_postgre.entity.Instructor;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.repository.InstructorRepository;
import com.example.student_lms_postgre.services.InstructorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<InstructorDto> findAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorDto> dto = new ArrayList<>();
        for (Instructor i : instructors) {
            InstructorDto temp = new InstructorDto();
            BeanUtils.copyProperties(i, temp);
            dto.add(temp);
        }
        return dto;
    }

    public InstructorDto getOne(Long id) {
        Instructor i = instructorRepository.getOne(id);
        InstructorDto dto = new InstructorDto();
        BeanUtils.copyProperties(i, dto);
        return dto;
    }

    public void registerForCourse(Long instructorId, Long courseId) {
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InvalidException("Invalid Instructor ID! Instructor does not exist!"));

        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidException("Invalid Course ID! Course does not exist!"));

        if (i.getCourse() != null) {
            throw new InvalidException("Instructor is already assigned to a course!");
        }
        i.setCourse(c);
        instructorRepository.save(i);
    }

    public void deregisterFromCourse(Long instructorId) {
        Instructor i = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InvalidException("Invalid Instructor ID! Instructor does not exist!"));

        if (i.getCourse() == null) {
            throw new InvalidException("Instructor is not assigned to any course!");
        }
        instructorRepository.delete(i);
    }
}
