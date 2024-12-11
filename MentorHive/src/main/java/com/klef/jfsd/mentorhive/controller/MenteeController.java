package com.klef.jfsd.mentorhive.controller;

import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.entity.Notification;
import com.klef.jfsd.mentorhive.repository.MenteeRepository;
import com.klef.jfsd.mentorhive.service.MenteeService;
import com.klef.jfsd.mentorhive.service.MentorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mentee")
public class MenteeController {

    @Autowired
    private MenteeService menteeService;
    
    @Autowired

    private MentorService mentorService;
    
    
    private MenteeRepository menteeRepository;

    // GET method to serve the registration page
    @GetMapping("/register")
    public String showRegistrationForm() {
    	 System.out.println("show form");
        return "mentee-register";  // Returns the mentee registration page
    }

    // POST method to handle the registration form submission
    @PostMapping("/register")
    public String registerMentee(@RequestParam String name,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,  // Add this annotation
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {
        
            // Handle profile picture upload
        	 // Get the file's original name
        	 System.out.println("Form submitted");
           


            Mentee mentee = new Mentee();
            mentee.setName(name);
            mentee.setDob(dob);
            mentee.setEmail(email);
            mentee.setPassword(password); // Add hashing later
           
            menteeService.saveMentee(mentee);
            System.out.println("Mentee saved to database.");
            return "redirect:/mentee/login";
        
    }
    
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", ""); // Initialize error message
        return "mentee-login";
    }
    
    
    @PostMapping("/login")
    public String loginMentee(@RequestParam String email, 
                              @RequestParam String password, 
                              Model model , HttpSession session) 
    {
        Mentee mentee = menteeService.findByEmail(email);
        if (mentee != null && mentee.getPassword().equals(password)) {
            model.addAttribute("mentee", mentee);
            session.setAttribute("loggedInMenteeEmail", mentee.getEmail());// Pass mentee to dashboard
            return "mentee-dashboard";  // Navigate to dashboard if successful
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "mentee-login";
        }

  }
    
    
    @GetMapping("/search")
    public String searchMentors(@RequestParam(required = false) String specialisation,
                                @RequestParam(required = false) String location,
                                @RequestParam(required = false) String skills,
                                Model model) {
        List<Mentor> mentors;
        if ((specialisation == null || specialisation.isEmpty()) && 
            (location == null || location.isEmpty()) && 
            (skills == null || skills.isEmpty())) {
            // Fetch all approved mentors if no criteria are provided
            mentors = mentorService.findMentorsByStatus("Approved");
        } else {
            // Fetch mentors based on criteria
            mentors = mentorService.searchMentors(specialisation, location, skills);
        }
        model.addAttribute("mentors", mentors);
        return "mentor-list"; // Render mentor results in this view
    }
    
    private Mentee getCurrentMentee(HttpSession session) {
        String menteeEmail = (String) session.getAttribute("loggedInMenteeEmail");
        if (menteeEmail != null) {
            return menteeService.findByEmail(menteeEmail);
        }
        return null;
    }
    
 
    
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInMenteeEmail");  // Remove the logged-in mentee from the session
        return "redirect:/login";  // Redirect to login page
    }

}
   



