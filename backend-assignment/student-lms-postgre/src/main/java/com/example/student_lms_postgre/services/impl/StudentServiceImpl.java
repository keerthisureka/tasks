package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.StudentCourseDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.*;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.exception.NotFoundException;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.repository.OrganizationRepository;
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
    OrganizationRepository organizationRepository;
    @Autowired
    StudentCourseRepository studentCourseRepository;

    @Override
    public void addStudent(String organizationId, StudentDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new InvalidException("Student name is not specified!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Date of Birth (dob) is not specified!");
        }
        Student student = new Student();
        BeanUtils.copyProperties(dto, student);
        student.setOrganization(organization);
        studentRepository.save(student);
        List<Student> students = organization.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        organization.setStudents(students);
        organizationRepository.save(organization);
    }

    @Override
    public void editStudent(String organizationId, String studentId, StudentDto dto) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId + ". Cannot edit student details!"));
        if (!student.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("Student not registered with the given organization ID!");
        }
        if (dto == null) {
            throw new InvalidException("Enter the student details to be updated!");
        }
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            student.setName(dto.getName());
        }
        if (dto.getDob() != null) {
            student.setDob(dto.getDob());
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String organizationId, String studentId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new NotFoundException("Organization not found with id: " + organizationId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with ID: " + studentId));
        if (!student.getOrganization().getId().equals(organizationId)) {
            throw new InvalidException("Student not registered with the given organization ID!");
        }
        studentRepository.deleteById(studentId);
        organization.getStudents().remove(student);
        organizationRepository.save(organization);
    }

    @Override
    public void enrollInCourse(String studentId, String courseId, CourseStatus status) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + courseId));
        if (studentCourseRepository.findByStudentAndCourse(studentId, courseId).isPresent()) {
            throw new InvalidException("Student is already enrolled in this course!");
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(new StudentCourseId(studentId, courseId));
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setStatus(status);
        studentCourseRepository.save(studentCourse);
        List<StudentCourse> studentCoursesS = student.getStudentCourses();
        if (studentCoursesS == null) {
            studentCoursesS = new ArrayList<>();
        }
        studentCoursesS.add(studentCourse);
        student.setStudentCourses(studentCoursesS);
        studentRepository.save(student);
        List<StudentCourse> studentCoursesC = course.getStudentCourses();
        if (studentCoursesC == null) {
            studentCoursesC = new ArrayList<>();
        }
        studentCoursesC.add(studentCourse);
        course.setStudentCourses(studentCoursesC);
        courseRepository.save(course);
    }

    @Override
    public void withdrawFromCourse(String studentId, String courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + courseId));
        if (!studentCourseRepository.findByStudentAndCourse(studentId, courseId).isPresent()) {
            throw new InvalidException("Student is not enrolled in this course!");
        }
        StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(studentId, courseId).get();
        studentCourseRepository.delete(studentCourse);

        student.getStudentCourses().remove(studentCourse);
        studentRepository.save(student);
        course.getStudentCourses().remove(studentCourse);
        courseRepository.save(course);
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
            temp.setCourseId(sc.getCourse().getId());
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
        Course c = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with the given ID: " + courseId + " does not exist!"));
        if (!organizationRepository.findCourseIdsByOrganization(organizationId).contains(courseId)) {
            throw new InvalidException("Course not offered by the given organization ID!");
        }
        List<String> studentIds = studentCourseRepository.findStudentsByCourseStatus(courseId, status.toString());
        List<Student> students = studentRepository.findAllById(studentIds);
        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            StudentDto temp = new StudentDto();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }
}
