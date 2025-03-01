package com.example.student_lms_mongo.services.impl;

import com.example.student_lms_mongo.dto.StudentCourseDto;
import com.example.student_lms_mongo.dto.StudentDto;
import com.example.student_lms_mongo.entity.Course;
import com.example.student_lms_mongo.entity.CourseStatus;
import com.example.student_lms_mongo.entity.Student;
import com.example.student_lms_mongo.entity.StudentCourse;
import com.example.student_lms_mongo.exception.InvalidException;
import com.example.student_lms_mongo.exception.NotFoundException;
import com.example.student_lms_mongo.repository.CourseRepository;
import com.example.student_lms_mongo.repository.StudentRepository;
import com.example.student_lms_mongo.services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addStudent(StudentDto dto) {
        if (dto.getName().isEmpty() || dto.getName() == null) {
            throw new InvalidException("Student name is not specified!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Date of Birth (dob) is not specified!");
        }
        if (dto.getOrganizationId() == null) {
            throw new InvalidException("Organization ID is not specified!");
        }
    }

    @Override
    public void editStudent(String id, StudentDto dto) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id + ". Cannot edit student details!"));
        if (dto == null) {
            throw new InvalidException("Enter the student details to be updated!");
        }
        if (dto.getName() != null || !dto.getName().isEmpty()) {
            s.setName(dto.getName());
        }
        if (dto.getDob() != null) {
            s.setDob(dto.getDob());
        }
        if (dto.getOrganizationId() != null) {
            s.setOrganizationId(dto.getOrganizationId());
        }
        if (dto.getStudentCourseDtos() != null) {
            List<StudentCourseDto> scDtos = dto.getStudentCourseDtos();
            List<StudentCourse> scList = s.getStudentCourses();

            for (StudentCourseDto scDto : scDtos) {
                Optional<StudentCourse> existingCourse = scList.stream()
                        .filter(sc -> sc.getCourseId().equals(scDto.getCourseId()))
                        .findFirst();

                if (existingCourse.isPresent()) {
                    existingCourse.get().setStatus(scDto.getStatus());
                } else {
                    StudentCourse newCourse = new StudentCourse();
                    newCourse.setCourseId(scDto.getCourseId());
                    newCourse.setStatus(scDto.getStatus());
                    scList.add(newCourse);
                }
            }
        }
        studentRepository.save(s);
    }

    @Override
    public void deleteStudent(String studentId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidException("Given student ID does not exist!"));
        studentRepository.deleteById(studentId);
    }

    @Override
    public void enrollInCourse(String studentId, String courseId, CourseStatus status) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidException("Student with the given ID: " + studentId + " does not exist!"));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidException("Course with the given ID: " + courseId + " does not exist!"));
        Optional<StudentCourse> existingCourse = s.getStudentCourses().stream()
                .filter(sc -> sc.getCourseId().equals(courseId)).findFirst();
        if (existingCourse.isPresent()) {
            throw new InvalidException("Student is already enrolled in this course!");
        }
        StudentCourse sc = new StudentCourse();
        sc.setCourseId(courseId);
        sc.setStatus(status);
        s.getStudentCourses().add(sc);
        studentRepository.save(s);
    }

    @Override
    public void withdrawFromCourse(String studentId, String courseId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidException("Student with the given ID: " + studentId + " does not exist!"));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new InvalidException("Course with the given ID: " + courseId + " does not exist!"));
        Optional<StudentCourse> existingCourse = s.getStudentCourses().stream()
                .filter(sc -> sc.getCourseId().equals(courseId)).findFirst();
        if (!existingCourse.isPresent()) {
            throw new InvalidException("Student is not enrolled in this course!");
        }
        s.getStudentCourses().remove(existingCourse.get());
    }

    @Override
    public List<StudentCourseDto> courseProgress(String studentId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidException("Student with the given ID: " + studentId + " does not exist!"));
        List<StudentCourse> scList = s.getStudentCourses();
        List<StudentCourseDto> scDtos = new ArrayList<>();
        for (StudentCourse sc : scList) {
            StudentCourseDto temp = new StudentCourseDto();
            BeanUtils.copyProperties(sc, temp);
            scDtos.add(temp);
        }
        return scDtos;
    }
}
