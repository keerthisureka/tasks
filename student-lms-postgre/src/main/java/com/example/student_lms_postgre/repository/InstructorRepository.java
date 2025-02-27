package com.example.student_lms_postgre.repository;

import com.example.student_lms_postgre.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query(value = "SELECT COUNT(*) FROM instructor WHERE organization_id = :organizationId", nativeQuery = true)
    Long countOfInstructors(@Param("organizationId") Long organizationId);

    @Query(value = "SELECT * FROM instructor WHERE course_id = :courseId", nativeQuery = true)
    List<Instructor> findInstructorsByCourseId(@Param("courseId") Long courseId);
}
