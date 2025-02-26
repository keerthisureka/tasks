package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.entity.Instructor;
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
}
