package com.klef.jfsd.mentorhive.dto;

public class CourseDTO {

    private String courseName;
    private String description;

    // Default constructor
    public CourseDTO() {}

    // Constructor
    public CourseDTO(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }

    // Getters and Setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
