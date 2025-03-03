package com.example.student_lms_postgre.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class CacheEntry implements Serializable {
    private String key;
    @JsonIgnore
    private String value;

    public CacheEntry() {}

    public CacheEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}