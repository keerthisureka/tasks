package com.example.feignOrg.feign;

import com.example.feignOrg.dto.ApiResponse;
import com.example.feignOrg.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PostgreStudentService", url = "http://localhost:8083")
public interface PostgreStudentServiceFeignClient {
    @GetMapping("/api/student/getStudentDetails/{id}")
    ResponseEntity<ApiResponse<StudentDto>> getStudentDetails(@PathVariable("id") String id);

    @PostMapping("/api/organization/addStudent/{organizationId}")
    ResponseEntity<ApiResponse<Void>> addStudent(@PathVariable("organizationId") String organizationId, @RequestBody StudentDto studentDto);
}
