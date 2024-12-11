package com.klef.jfsd.mentorhive.controller;

import com.klef.jfsd.mentorhive.dto.CourseDTO;
import com.klef.jfsd.mentorhive.entity.Course;
import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.repository.CourseRepository;
import com.klef.jfsd.mentorhive.repository.MentorRepository;
import com.klef.jfsd.mentorhive.service.CourseService;
import com.klef.jfsd.mentorhive.service.MenteeService;
import com.klef.jfsd.mentorhive.service.MentorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private MentorService mentorService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private MenteeService menteeService;
    
    private MentorRepository mentorRepository;
    
    private CourseRepository courseRepository;
    
    
    // Show Login Form
    @GetMapping("/login")
    public String showLoginForm() {
        return "mentor-login";
    }

    // Handle Login Submission
    @PostMapping("/login")
    public String mentorLogin(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        Mentor mentor = mentorService.findMentorByEmailAndPassword(email, password);
        
        if (mentor != null) {
            if ("Approved".equals(mentor.getStatus())) {
                session.setAttribute("loggedInMentor", mentor); // Store mentor in session
                return "redirect:/mentor/dashboard";  
            } else {
                model.addAttribute("error", "Your mentor application is not approved yet.");
                return "mentor-login";  
            }
        } else {
            model.addAttribute("error", "Invalid credentials.");
            return "mentor-login";  
        }
    }


    // Show Registration Form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "mentor-register";
    }

    // Handle Registration Submission
    @PostMapping("/register")
    public String registerMentor(@RequestParam String name,
                                 @RequestParam LocalDate dob,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String specialisation,
                                 @RequestParam String skills,
                                 @RequestParam String location,
                                 @RequestParam int availability,
                                 @RequestParam int age,
                                 @RequestParam(required = false) String bio) {

        Mentor mentor = new Mentor();
        mentor.setAge(age);
        mentor.setAvailability(availability);
        mentor.setLocation(location);
        mentor.setSpecialisation(specialisation);
        mentor.setName(name);
        mentor.setDob(dob);
        mentor.setEmail(email);
        mentor.setPassword(password); // Add hashing later
        mentor.setSkills(skills);
        mentor.setBio(bio);
        mentor.setStatus("Pending"); // Default status for admin approval
        mentor.setCreatedAt(LocalDate.now());

        mentorService.saveMentor(mentor);

        return "redirect:/mentor/login"; // Redirect to login page after registration
    }
    
    
    @GetMapping
    public String listMentors(Model model) {
        List<Mentor> mentors = mentorService.findAllApprovedMentors();
        model.addAttribute("mentors", mentors);
        return "mentor-list";
    }

    @GetMapping("/details")
    public String mentorDetails(@RequestParam("id") Long id, Model model) {
        Mentor mentor = mentorService.findMentorById(id);
        model.addAttribute("mentor", mentor);
        return "mentor-details";
    }


    @GetMapping("/mentor/{mentorId}")
    public List<Course> getCoursesByMentor(@PathVariable Long mentorId) {
        return courseService.getCoursesByMentor(mentorId);
    }


    
 // Show the mentor dashboard
    @GetMapping("/dashboard")
    public String showMentorDashboard(HttpSession session, Model model) {
        Mentor mentor = (Mentor) session.getAttribute("loggedInMentor"); // Get mentor from session
        
        if (mentor == null) {
            return "redirect:/mentor/login"; // Redirect to login if no mentor in session
        }

        // Fetch courses and mentees assigned to the mentor
        List<Course> courses = courseService.getCoursesByMentor(mentor.getId());
        model.addAttribute("mentor", mentor); 
        model.addAttribute("courses", courses); // Add courses to model
        return "mentor-dashboard";  
    }

    // Show course creation form
    @GetMapping("/create-course")
    public String showCourseCreationForm(HttpSession session, Model model) {
        Mentor mentor = (Mentor) session.getAttribute("loggedInMentor"); // Get mentor from session
        
        if (mentor == null) {
            return "redirect:/mentor/login"; // Redirect to login if no mentor in session
        }

        // Fetch the list of mentees for the mentor
        List<Mentee> mentees = mentorService.getMenteesByMentorId(mentor.getId());
        model.addAttribute("mentor", mentor); // Add mentor to model
        model.addAttribute("mentees", mentees); // Add mentees to model
        return "create-course"; // Show course creation form
    }

    // Handle course creation submission
    @PostMapping("/create-course")
    public String createCourse(@RequestParam String courseName,
                               @RequestParam String description,
                               @RequestParam Long mentorId,
                               @RequestParam List<Long> menteeIds,
                               HttpSession session) {
        Mentor mentor = (Mentor) session.getAttribute("loggedInMentor");
        if (mentor == null) {
            return "redirect:/mentor/login";
        }

        courseService.createCourse(courseName, description, mentorId, menteeIds);
        return "redirect:/mentor/dashboard"; // Redirect to dashboard after creating course
    }

   

}