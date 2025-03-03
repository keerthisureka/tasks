package com.example.student_lms_postgre.repository;

import com.example.student_lms_postgre.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {
    @Query(value = "SELECT id FROM student WHERE organization_id = :organizationId", nativeQuery = true)
    HashSet<String> findStudentIdsByOrganization(@Param("organizationId") String organizationId);

    @Query(value = "SELECT id FROM instructor WHERE organization_id = :organizationId", nativeQuery = true)
    HashSet<String> findInstructorIdsByOrganization(@Param("organizationId") String organizationId);

    @Query(value = "SELECT id FROM course WHERE organization_id = :organizationId", nativeQuery = true)
    HashSet<String> findCourseIdsByOrganization(@Param("organizationId") String organizationId);
}
