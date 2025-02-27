package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.*;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.repository.StudentCourseRepository;
import com.example.student_lms_postgre.repository.StudentRepository;
import com.example.student_lms_postgre.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentCourseRepository studentCourseRepository;

    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            StudentDto temp = new StudentDto();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }

    public StudentDto getOne(Long id) {
        Student s = studentRepository.getOne(id);
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(s, dto);
        return dto;
    }

    public void enrollInCourse(Long studentId, Long courseId, CourseStatus status) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidException("Invalid Student ID! Student does not exist!"));

        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidException("Invalid Course ID! Course does not exist!"));

        if (!CourseStatus.isValid(status)) {
            throw new InvalidException("Invalid Course Status! Status must be one of the following: [TO_DO, IN_PROGRESS, COMPLETED].");
        }

        StudentCourseId scid = new StudentCourseId();
        scid.setStudent_id(studentId);
        scid.setCourse_id(courseId);
        Optional<StudentCourse> existingEnrollment = studentCourseRepository.findById(scid);
        if (existingEnrollment.isPresent()) {
            throw new InvalidException("Student is already enrolled in this course!");
        }

        StudentCourse sc = new StudentCourse();
        sc.setId(scid);
        sc.setStudent(s);
        sc.setCourse(c);
        sc.setStatus(status);
        studentCourseRepository.save(sc);
    }

    public void withdrawFromCourse(Long studentId, Long courseId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidException("Invalid Student ID! Student does not exist!"));

        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidException("Invalid Course ID! Course does not exist!"));

        StudentCourseId scid = new StudentCourseId();
        scid.setStudent_id(studentId);
        scid.setCourse_id(courseId);
        StudentCourse existingEnrollment = studentCourseRepository.findById(scid)
                .orElseThrow(() -> new InvalidException("Student is not enrolled in the specified course!"));

        studentCourseRepository.delete(existingEnrollment);
    }

    public List<Map<String, Object>> courseProgress(Long studentId) {
        List<StudentCourse> scs = studentCourseRepository.findByStudent_Id(studentId);
        if (scs.isEmpty()) {
            throw new InvalidException("Invalid Student ID! Student is not enrolled in any course!");
        }

        List<Map<String, Object>> response = new ArrayList<>();
        for (StudentCourse sc : scs) {
            Map<String, Object> courseData = new HashMap<>();
            courseData.put("course_id", sc.getCourse().getId());
            courseData.put("status", sc.getStatus());
            response.add(courseData);
        }
        return response;
    }

    public int getCountOfStudentsInEachCourse(Long courseId) {
        List<StudentCourse> scs = studentCourseRepository.findByCourse_Id(courseId);
        return scs.size();
    }
}
