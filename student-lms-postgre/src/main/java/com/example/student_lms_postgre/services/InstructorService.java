package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.InstructorDto;

import java.util.List;

public interface InstructorService {
    public List<InstructorDto> findAll();

    public InstructorDto getOne(Long id);

    public void registerForCourse(Long instructorId, Long courseId);

    public void deregisterFromCourse(Long instructorId);

    public Long countOfInstructors(Long organizationId);
}
