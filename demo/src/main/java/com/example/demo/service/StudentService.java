package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Dept;
import com.example.demo.entity.Student;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.DeptRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DeptRepository deptRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public String getStudentCount(Long dept_id) {
        long count = studentRepository.findAll().stream()
                .filter(student -> student.getDept().getId().equals(dept_id))
                .count();

        if(count > 0)
            return "Count of students with dept id: " + dept_id + " is: " + count;
        else
            return "Department not fount!";
    }

    public List<Student> getStudentsInCity(String city) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAddress().getCity().equals(city))
                .collect(Collectors.toList());
    }

    public String createStudent(StudentDto s) {
        Student entityStud = new Student();
        BeanUtils.copyProperties(s, entityStud);

        Dept entityDept = null;
        if (s.getDeptDto() != null) {
            entityDept = deptRepository.findByDeptName(s.getDeptDto().getDeptName())
                    .orElseGet(() -> {
                        Dept newDept = new Dept();
                        BeanUtils.copyProperties(s.getDeptDto(), newDept);
                        return deptRepository.save(newDept);
                    });
        }
        entityStud.setDept(entityDept);

        entityStud = studentRepository.save(entityStud);

        if (s.getAddressDto() != null) {
            Address entityAddress = new Address();
            BeanUtils.copyProperties(s.getAddressDto(), entityAddress);

            entityAddress.setStudent(entityStud);

            addressRepository.save(entityAddress);

            entityStud.setAddress(entityAddress);
            studentRepository.save(entityStud); // Update
        }

        return s.toString();
    }
}
