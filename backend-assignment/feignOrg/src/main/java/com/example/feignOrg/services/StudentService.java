package com.example.feignOrg.services;

import com.example.feignOrg.dto.ApiResponse;
import com.example.feignOrg.dto.StudentDto;
import com.example.feignOrg.exception.InvalidException;
import com.example.feignOrg.exception.NotFoundException;
import com.example.feignOrg.feign.MongoStudentServiceFeignClient;
import com.example.feignOrg.feign.PostgreStudentServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    MongoStudentServiceFeignClient mongoStudentServiceFeignClient;

    @Autowired
    PostgreStudentServiceFeignClient postgreStudentServiceFeignClient;

    public ResponseEntity<ApiResponse<StudentDto>> getStudentDetails(boolean choice, String studentId) {
        if (choice) {
            try {
                return mongoStudentServiceFeignClient.getStudentDetails(studentId);
            } catch (Exception e) {
                throw new NotFoundException("Student with the given ID: " + studentId + " does not exist!");
            }
        }
        else {
            try {
                return postgreStudentServiceFeignClient.getStudentDetails(studentId);
            } catch (Exception e) {
                throw new NotFoundException("Student with the given ID: " + studentId + " does not exist!");
            }
        }
    }

    public ResponseEntity<ApiResponse<Void>> addStudent(boolean choice, String organizationId, StudentDto studentDto) {
        if (choice) {
            try {
                return mongoStudentServiceFeignClient.addStudent(organizationId, studentDto);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        else {
            try {
                return postgreStudentServiceFeignClient.addStudent(organizationId, studentDto);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
