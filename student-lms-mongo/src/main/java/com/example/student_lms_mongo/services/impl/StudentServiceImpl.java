package com.example.student_lms_mongo.services.impl;

import com.example.student_lms_mongo.dto.StudentCourseDto;
import com.example.student_lms_mongo.dto.StudentDto;
import com.example.student_lms_mongo.entity.*;
import com.example.student_lms_mongo.exception.InvalidException;
import com.example.student_lms_mongo.exception.NotFoundException;
import com.example.student_lms_mongo.repository.CourseRepository;
import com.example.student_lms_mongo.repository.OrganizationRepository;
import com.example.student_lms_mongo.repository.StudentRepository;
import com.example.student_lms_mongo.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void addStudent(String organizationId, StudentDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (dto.getName().isEmpty() || dto.getName() == null) {
            throw new InvalidException("Student name is not specified!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Date of Birth (dob) is not specified!");
        }
        Student s = new Student();
        BeanUtils.copyProperties(dto, s);
        s.setOrganizationId(organizationId);
        studentRepository.save(s);
        List<String> studentIds = organization.getStudentIds();
        if (studentIds == null) {
            studentIds = new ArrayList<>();
        }
        studentIds.add(s.getId());
        organization.setStudentIds(studentIds);
        organizationRepository.save(organization);
    }

    @Override
    public void editStudent(String organizationId, String studentId, StudentDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId + ". Cannot edit student details!"));
        if (!s.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("Student not registered with the given organization ID!");
        }
        if (dto == null) {
            throw new InvalidException("Enter the student details to be updated!");
        }
        if (dto.getName() != null || !dto.getName().isEmpty()) {
            s.setName(dto.getName());
        }
        if (dto.getDob() != null) {
            s.setDob(dto.getDob());
        }
//        if (dto.getStudentCourseDtos() != null) {
//            List<StudentCourseDto> scDtos = dto.getStudentCourseDtos();
//            List<StudentCourse> scList = s.getStudentCourses();
//
//            for (StudentCourseDto scDto : scDtos) {
//                Optional<StudentCourse> existingCourse = scList.stream()
//                        .filter(sc -> sc.getCourseId().equals(scDto.getCourseId()))
//                        .findFirst();
//
//                if (existingCourse.isPresent()) {
//                    existingCourse.get().setStatus(scDto.getStatus());
//                } else {
//                    StudentCourse newCourse = new StudentCourse();
//                    newCourse.setCourseId(scDto.getCourseId());
//                    newCourse.setStatus(scDto.getStatus());
//                    scList.add(newCourse);
//                }
//            }
//        }
        studentRepository.save(s);
    }

    @Override
    public void deleteStudent(String organizationId, String studentId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
        if (!s.getOrganizationId().equals(organizationId)) {
            throw new InvalidException("Student not registered with the given organization ID!");
        }
        studentRepository.deleteById(studentId);
        organization.getStudentIds().remove(studentId);
        organizationRepository.save(organization);
    }

    @Override
    public void enrollInCourse(String studentId, String courseId, CourseStatus status) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with the given ID: " + studentId + " does not exist!"));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with the given ID: " + courseId + " does not exist!"));
        if (s.getStudentCourses() == null) {
            s.setStudentCourses(new ArrayList<>());
        }
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
        List<String> studentIds = c.getStudentIds();
        if (studentIds == null) {
            studentIds = new ArrayList<>();
        }
        studentIds.add(studentId);
        c.setStudentIds(studentIds);
        courseRepository.save(c);
    }

    @Override
    public void withdrawFromCourse(String studentId, String courseId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with the given ID: " + studentId + " does not exist!"));
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with the given ID: " + courseId + " does not exist!"));
        Optional<StudentCourse> existingCourse = s.getStudentCourses().stream()
                .filter(sc -> sc.getCourseId().equals(courseId)).findFirst();
        if (!existingCourse.isPresent()) {
            throw new InvalidException("Student is not enrolled in this course!");
        }
        s.getStudentCourses().remove(existingCourse.get());
        studentRepository.save(s);
        c.getStudentIds().remove(studentId);
        courseRepository.save(c);
    }

    @Override
    public List<StudentCourseDto> courseProgress(String studentId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with the given ID: " + studentId + " does not exist!"));
        List<StudentCourse> scList = s.getStudentCourses();
        List<StudentCourseDto> scDtos = new ArrayList<>();
        for (StudentCourse sc : scList) {
            StudentCourseDto temp = new StudentCourseDto();
            BeanUtils.copyProperties(sc, temp);
            scDtos.add(temp);
        }
        return scDtos;
    }

    @Override
    public StudentDto getStudentDetails(String studentId) {
        Student s = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student with the given ID: " + studentId + " does not exist!"));
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(s, dto);
        return dto;
    }

    // Organization
    @Override
    public List<StudentDto> findStudentByCourseStatus(String organizationId, String courseId, CourseStatus status) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        List<Student> students = studentRepository.findStudentsByCourseStatus(courseId, status);
        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            StudentDto temp = new StudentDto();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }
}
