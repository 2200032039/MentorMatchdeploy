package com.klef.jfsd.mentorhive.controller;

import com.klef.jfsd.mentorhive.dto.CourseRequest;
import com.klef.jfsd.mentorhive.entity.Course;
import com.klef.jfsd.mentorhive.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Endpoint for creating a course (Mentor's side)
//    @PostMapping("/create")
//    public Course createCourse(@RequestBody CourseRequest courseRequest) {
//        return courseService.createCourse(courseRequest.getCourseName(), courseRequest.getDescription(),
//                courseRequest.getMentorId(), courseRequest.getMenteeIds());
//    }

    // Endpoint for mentees to enroll in a course
    @PostMapping("/{courseId}/enroll/{menteeId}")
    public void enrollInCourse(@PathVariable Long courseId, @PathVariable Long menteeId) {
        courseService.enrollInCourse(courseId, menteeId);
    }

    // Endpoint to get courses for a mentee (Mentee's dashboard)
    @GetMapping("/mentee/{menteeId}")
    public List<Course> getCoursesForMentee(@PathVariable Long menteeId) {
        return courseService.getCoursesForMentee(menteeId);
    }

    // Endpoint to get courses for a mentor
    @GetMapping("/mentor/{mentorId}")
    public List<Course> getCoursesForMentor(@PathVariable Long mentorId) {
        return courseService.getCoursesForMentor(mentorId);
    }
}
