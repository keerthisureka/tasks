package com.example.student_lms_postgre.controller;

import com.example.student_lms_postgre.dto.ApiResponse;
import com.example.student_lms_postgre.dto.CacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/instructors")
    public ResponseEntity<ApiResponse<List<CacheEntry>>> getCacheData() {
        Cache cache = cacheManager.getCache("instructors");
        if (cache == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Cache not found!", HttpStatus.NOT_FOUND, Collections.emptyList()));
        }
        if (cache.getNativeCache() instanceof com.github.benmanes.caffeine.cache.Cache) {
            com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeineCache =
                    (com.github.benmanes.caffeine.cache.Cache<Object, Object>) cache.getNativeCache();
            List<CacheEntry> cacheContents = new ArrayList<>();
            caffeineCache.asMap().forEach((key, value) -> cacheContents.add(new CacheEntry(key.toString(), value.toString())));
            return ResponseEntity.ok(new ApiResponse<>("Cache retrieved successfully!", HttpStatus.OK, cacheContents));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>("Error retrieving cache data!", HttpStatus.INTERNAL_SERVER_ERROR, Collections.emptyList()));
    }
}
