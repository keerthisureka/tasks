package com.example.student_lms_postgre.repository;

import com.example.student_lms_postgre.entity.CourseStatus;
import com.example.student_lms_postgre.entity.StudentCourse;
import com.example.student_lms_postgre.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
    @Query(value = "SELECT * FROM student_course sc WHERE sc.student_id = :studentId AND sc.course_id = :courseId", nativeQuery = true)
    Optional<StudentCourse> findByStudentAndCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);

    @Query(value = "SELECT sc.student_id FROM student_course sc WHERE sc.course_id = :courseId AND sc.status = CAST(:status AS VARCHAR)", nativeQuery = true)
    List<String> findStudentsByCourseStatus(@Param("courseId") String courseId, @Param("status") String status);
}
