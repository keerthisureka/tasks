package com.example.student_lms_mongo.repository;

import com.example.student_lms_mongo.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
