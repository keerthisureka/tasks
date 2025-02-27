package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.OrganizationDto;
import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.*;
import com.example.student_lms_postgre.exception.InvalidException;
import com.example.student_lms_postgre.exception.NotFoundException;
import com.example.student_lms_postgre.repository.*;
import com.example.student_lms_postgre.services.CourseService;
import com.example.student_lms_postgre.services.InstructorService;
import com.example.student_lms_postgre.services.OrganizationService;
import com.example.student_lms_postgre.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    @Autowired
    StudentCourseRepository studentCourseRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    InstructorService instructorService;
    @Autowired
    CourseService courseService;

    public List<OrganizationDto> findAllOrganizations() {
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
    public List<StudentDto> getAllStudents(Long id) {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new NotFoundException("No students found!");
        }
        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            if (s.getOrganization().getId() == id) {
                StudentDto temp = new StudentDto();
                BeanUtils.copyProperties(s, temp);
                dto.add(temp);
            }
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
        if (dto.getOrganizationId() == null) {
            throw new InvalidException("Student's organization_id is missing!");
        }
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new InvalidException("Organization not found!"));
        Student s = new Student();
        BeanUtils.copyProperties(dto, s);
        s.setOrganization(organization);
        studentRepository.save(s);
    }

    public void editStudent(StudentDto dto) {
        Student s = studentRepository.findById(dto.getId()).orElse(null);
        if (s == null) {
            throw new NotFoundException("Student not found with id: " + dto.getId() + ". Cannot edit student details!");
        }
        if (dto.getName() != null && dto.getDob() != null) {
            s.setName(dto.getName());
            s.setDob(dto.getDob());
        }
        else if (dto.getName() != null) {
            s.setName(dto.getName());
        }
        else if (dto.getDob() != null) {
            s.setDob(dto.getDob());
        }
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

    @Override
    public void enrollInCourse(Long studentId, Long courseId, CourseStatus status) {
        studentService.enrollInCourse(studentId, courseId, status);
    }

    @Override
    public void withdrawFromCourse(Long studentId, Long courseId) {
        studentService.withdrawFromCourse(studentId, courseId);
    }

    @Override
    public int getCountOfStudentsInEachCourse(Long courseId) {
        return studentService.getCountOfStudentsInEachCourse(courseId);
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
        if (dto.getOrganizationId() == null) {
            throw new InvalidException("Instructor's organization_id is missing!");
        }
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new InvalidException("Organization not found!"));
        Instructor i = new Instructor();
        BeanUtils.copyProperties(dto, i);
        i.setOrganization(organization);
        instructorRepository.save(i);
    }

    public void editInstructor(InstructorDto dto) {
        Instructor i = instructorRepository.findById(dto.getId()).orElse(null);
        if (i == null) {
            throw new NotFoundException("Instructor not found with id: " + dto.getId() + ". Cannot edit instructor details!");
        }
        if (dto.getName() != null && dto.getDob() != null) {
            i.setName(dto.getName());
            i.setDob(dto.getDob());
        }
        else if (dto.getName() != null) {
            i.setName(dto.getName());
        }
        else if (dto.getDob() != null) {
            i.setDob(dto.getDob());
        }
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

    @Override
    public void registerForCourse(Long instructorId, Long courseId) {
        instructorService.registerForCourse(instructorId, courseId);
    }

    @Override
    public void deregisterFromCourse(Long instructorId) {
        instructorService.deregisterFromCourse(instructorId);
    }

    @Override
    public Long countOfInstructors(Long organizationId) {
        return instructorService.countOfInstructors(organizationId);
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
        if (dto.getOrganizationId() == null) {
            throw new InvalidException("Course's organization_id is missing!");
        }
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new InvalidException("Organization not found!"));

        Course c = new Course();
        BeanUtils.copyProperties(dto, c);
        c.setOrganization(organization);
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

    public List<InstructorDto> getInstructorsForCourse(Long courseId) {
        return courseService.getInstructorsForCourse(courseId);
    }

    public List<StudentDto> students(Long courseId) {
        List<StudentCourse> scs = studentCourseRepository.findByCourse_Id(courseId);
        if (scs.isEmpty()) {
            throw new NotFoundException("No students enrolled in this course!");
        }
        List<Student> students = new ArrayList<>();
        for (StudentCourse sc : scs) {
            students.add(sc.getStudent());
        }

        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            StudentDto temp = new StudentDto();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }

    public List<InstructorDto> instructors(Long courseId) {
        List<Instructor> instructors = instructorRepository.findInstructorsByCourseId(courseId);

        List<InstructorDto> dto = new ArrayList<>();
        for (Instructor i : instructors) {
            InstructorDto temp = new InstructorDto();
            BeanUtils.copyProperties(i, temp);
            dto.add(temp);
        }
        return dto;
    }
}
