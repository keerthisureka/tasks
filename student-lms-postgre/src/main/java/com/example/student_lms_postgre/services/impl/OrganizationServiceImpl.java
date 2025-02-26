package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.OrganizationDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.Course;
import com.example.student_lms_postgre.entity.Instructor;
import com.example.student_lms_postgre.entity.Organization;
import com.example.student_lms_postgre.entity.Student;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.exception.NotFoundException;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.repository.InstructorRepository;
import com.example.student_lms_postgre.repository.OrganizationRepository;
import com.example.student_lms_postgre.repository.StudentRepository;
import com.example.student_lms_postgre.services.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<OrganizationDto> findAll() {
        List<Organization> organizations = organizationRepository.findAll();
        if (organizations.isEmpty()) {
            throw new NotFoundException("No organizations found!");
        }
        List<OrganizationDto> dto = new ArrayList<>();
        for (Organization o : organizations) {
            OrganizationDto temp = new OrganizationDto();
            BeanUtils.copyProperties(o, temp);
            dto.add(temp);
        }
        return dto;
    }

    public void createOrganization(OrganizationDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Organization name is missing!");
        }
        Organization o = new Organization();
        BeanUtils.copyProperties(dto, o);
        organizationRepository.save(o);
    }

    // Student
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new NotFoundException("No students found!");
        }
        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            StudentDto temp = new StudentDto();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }

    public StudentDto getStudentById(Long id) {
        Student s = studentRepository.getOne(id);
        if (s == null) {
            throw new NotFoundException("Student Id: " + id + " does not exist!");
        }
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(s, dto);
        return dto;
    }

    public void addStudent(StudentDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Student name is missing!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Student Date of Birth (dob) is missing!");
        }
        Student s = new Student();
        BeanUtils.copyProperties(dto, s);
        studentRepository.save(s);
    }

    public void editStudent(StudentDto dto) {
        Student s = studentRepository.findById(dto.getId()).orElse(null);
        if (s == null) {
            throw new NotFoundException("Student not found with id: " + dto.getId() + ". Cannot edit student details!");
        }
        s.setName(dto.getName());
        s.setDob(dto.getDob());
        studentRepository.save(s);
    }

    public void deleteStudent(Long id) {
        Student s = studentRepository.findById(id).orElse(null);
        if (s != null) {
            studentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Student not found with id: " + id);
        }
    }

    // Instructor
    public List<InstructorDto> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        if (instructors.isEmpty()) {
            throw new NotFoundException("No instructors found!");
        }
        List<InstructorDto> dto = new ArrayList<>();
        for (Instructor i : instructors) {
            InstructorDto temp = new InstructorDto();
            BeanUtils.copyProperties(i, temp);
            dto.add(temp);
        }
        return dto;
    }

    public InstructorDto getInstructorById(Long id) {
        Instructor i = instructorRepository.getOne(id);
        if (i == null) {
            throw new NotFoundException("Instructor Id: " + id + " does not exist!");
        }
        InstructorDto dto = new InstructorDto();
        BeanUtils.copyProperties(i, dto);
        return dto;
    }

    public void addInstructor(InstructorDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Instructor name is missing!");
        }
        if (dto.getDob() == null) {
            throw new InvalidException("Instructor Date of Birth (dob) is missing!");
        }

        Instructor i = new Instructor();
        BeanUtils.copyProperties(dto, i);
        instructorRepository.save(i);
    }

    public void editInstructor(InstructorDto dto) {
        Instructor i = instructorRepository.findById(dto.getId()).orElse(null);
        if (i == null) {
            throw new NotFoundException("Instructor not found with id: " + dto.getId() + ". Cannot edit instructor details!");
        }

        i.setName(dto.getName());
        i.setDob(dto.getDob());
        instructorRepository.save(i);
    }

    public void deleteInstructor(Long id) {
        Instructor i = instructorRepository.findById(id).orElse(null);
        if (i != null) {
            instructorRepository.deleteById(id);
        } else {
            throw new NotFoundException("Instructor not found with id: " + id);
        }
    }

    // Course
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new NotFoundException("No courses found!");
        }
        List<CourseDto> dto = new ArrayList<>();
        for (Course c : courses) {
            CourseDto temp = new CourseDto();
            BeanUtils.copyProperties(c, temp);
            dto.add(temp);
        }
        return dto;
    }

    public CourseDto getCourseById(Long id) {
        Course c = courseRepository.getOne(id);
        if (c == null) {
            throw new NotFoundException("Course Id: " + id + " does not exist!");
        }
        CourseDto dto = new CourseDto();
        BeanUtils.copyProperties(c, dto);
        return dto;
    }

    public void addCourse(CourseDto dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new InvalidException("Course name is missing!");
        }
        if (dto.getFee() <= 0.0) {
            throw new InvalidException("Course fee is missing!");
        }
        Course c = new Course();
        BeanUtils.copyProperties(dto, c);
        courseRepository.save(c);
    }

    public void deleteCourse(Long id) {
        Course c = courseRepository.findById(id).orElse(null);
        if (c != null) {
            courseRepository.deleteById(id);
        } else {
            throw new NotFoundException("Course not found with id: " + id);
        }
    }
}
