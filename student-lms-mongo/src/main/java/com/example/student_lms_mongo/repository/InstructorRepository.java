package com.example.student_lms_mongo.repository;

import com.example.student_lms_mongo.entity.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstructorRepository extends MongoRepository<Instructor, String> {
}
