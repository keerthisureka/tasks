package com.example.student_lms_postgre.entity;

public enum CourseStatus {
    TO_DO,
    IN_PROGRESS,
    COMPLETED;

    public static boolean isValid(CourseStatus status) {
        return status == TO_DO || status == IN_PROGRESS || status == COMPLETED;
    }
}