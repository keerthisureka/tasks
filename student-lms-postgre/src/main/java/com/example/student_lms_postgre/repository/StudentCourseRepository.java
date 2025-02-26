package com.example.student_lms_postgre.repository;

import com.example.student_lms_postgre.entity.StudentCourse;
import com.example.student_lms_postgre.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
    List<StudentCourse> findByStudent_Id(Long studentId);

    List<StudentCourse> findByCourse_Id(Long courseId);
}
