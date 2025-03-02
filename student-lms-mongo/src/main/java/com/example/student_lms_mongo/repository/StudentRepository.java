package com.example.student_lms_mongo.repository;

import com.example.student_lms_mongo.entity.CourseStatus;
import com.example.student_lms_mongo.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{ 'studentCourses.status': ?0 }")
    public List<Student> findStudentsByCourseStatus(CourseStatus status);
}
