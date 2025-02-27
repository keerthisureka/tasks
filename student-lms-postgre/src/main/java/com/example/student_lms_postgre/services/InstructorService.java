package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.entity.CourseStatus;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorService {
    public List<InstructorDto> findAll();

    public InstructorDto getOne(Long id);

    public void registerForCourse(Long instructorId, Long courseId);

    public void deregisterFromCourse(Long instructorId);

//    @Query("SELECT COUNT(*) FROM Organization o, Instructor i WHERE o.id = i.organization_id", nativeQuery = true)
    public Long countOfInstructors(Long organizationId);

    public void updateStudentStatus(Long instructorId, Long studentId, Long courseId, CourseStatus status);
}
