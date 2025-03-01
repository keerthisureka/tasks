package com.example.student_lms_mongo.repository;

import com.example.student_lms_mongo.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    @Query("{ 'studentCourses.status': ?0 }")
    public List<Student> findStudentsByCourseStatus(String status);
}
