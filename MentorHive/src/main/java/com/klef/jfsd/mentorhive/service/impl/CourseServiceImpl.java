package com.klef.jfsd.mentorhive.service.impl;

import com.klef.jfsd.mentorhive.dto.CourseDTO;
import com.klef.jfsd.mentorhive.entity.Course;
import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.repository.CourseRepository;
import com.klef.jfsd.mentorhive.repository.MenteeRepository;
import com.klef.jfsd.mentorhive.repository.MentorRepository;
import com.klef.jfsd.mentorhive.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MenteeRepository menteeRepository;

    // Method to create a course and select mentees
    public Course createCourse(String courseName, String description, Long mentorId, List<Long> menteeIds) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new RuntimeException("Mentor not found"));

        // Create the course
        Course course = new Course(courseName, description, mentor);

        // Fetch the mentees and associate with the course
        for (Long menteeId : menteeIds) {
            Mentee mentee = menteeRepository.findById(menteeId).orElseThrow(() -> new RuntimeException("Mentee not found"));
            course.addMentee(mentee);
        }

        // Save the course
        return courseRepository.save(course);
    }

    // Method for mentees to enroll in a course
    public void enrollInCourse(Long courseId, Long menteeId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Mentee mentee = menteeRepository.findById(menteeId).orElseThrow(() -> new RuntimeException("Mentee not found"));

        // Add mentee to the course
        course.addMentee(mentee);
        courseRepository.save(course);
    }

    // Method to fetch all courses for a mentee
    public List<Course> getCoursesForMentee(Long menteeId) {
        Mentee mentee = menteeRepository.findById(menteeId).orElseThrow(() -> new RuntimeException("Mentee not found"));
        return courseRepository.findByMenteesContains(mentee);
    }

    // Method to get all courses for a mentor
    public List<Course> getCoursesForMentor(Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new RuntimeException("Mentor not found"));
        return courseRepository.findByMentor(mentor);
    }

    public Course createCourse(CourseDTO courseDTO, Long mentorId, List<Long> menteeIds) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new IllegalArgumentException("Mentor not found"));
        List<Mentee> mentees = menteeRepository.findAllById(menteeIds);

        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setDescription(courseDTO.getDescription());
        course.setMentor(mentor);
        course.setMentees(mentees);

        return courseRepository.save(course);
    }

    public List<Course> getCoursesByMentor(Long mentorId) {
        return courseRepository.findByMentorId(mentorId);
    }
}
