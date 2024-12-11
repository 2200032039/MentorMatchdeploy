package com.klef.jfsd.mentorhive.controller;

import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.repository.MenteeRepository;
import com.klef.jfsd.mentorhive.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
    @Autowired
    private MentorService mentorService;
    
    @Autowired
    private MenteeRepository menteeRepository;
    
    @PostMapping("/approve")
    public String approveMentor(@RequestParam Long id) {
        mentorService.updateMentorStatus(id, "Approved");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/reject")
    public String rejectMentor(@RequestParam Long id) {
        mentorService.updateMentorStatus(id, "Rejected");
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
    	long activeMentorsCount = mentorService.countMentorsByStatus("Approved");
        long pendingMentorsCount = mentorService.countMentorsByStatus("Pending");
        long rejectedMentorsCount = mentorService.countMentorsByStatus("Rejected");

        
        
         
        model.addAttribute("activeMentors", activeMentorsCount);
        model.addAttribute("pendingMentors", pendingMentorsCount);
        model.addAttribute("rejectedMentors", rejectedMentorsCount);
      
        System.out.println("Active Mentors Count: " + activeMentorsCount);
        System.out.println("Pending Mentors Count: " + pendingMentorsCount);
        System.out.println("Rejected Mentors Count: " + rejectedMentorsCount);


        return "admin-dashboard";
    }

    
    @GetMapping("/pending-mentors")
    public String showPendingMentors(Model model) {
        List<Mentor> pendingMentors = mentorService.findMentorsByStatus("Pending");
        model.addAttribute("pendingMentorsList", pendingMentors);
        return "admin-pending-mentors";  // Redirect to the page displaying pending mentors
    }
    
    @GetMapping("/mentor-details/{id}")
    public String showMentorDetails(@PathVariable Long id, Model model) {
        Mentor mentor = mentorService.findMentorById(id);
        model.addAttribute("mentor", mentor);
        return "admin-mentor-details";  // Redirect to the mentor details page
    }
    
    @GetMapping("/match")
    public String showMatchingPage(Model model) {
        List<Mentor> availableMentors = mentorService.findAvailableMentors();
        List<Mentee> unassignedMentees = menteeRepository.findByMentorId(null); // Unassigned mentees
        model.addAttribute("availableMentors", availableMentors);
        model.addAttribute("unassignedMentees", unassignedMentees);
        return "admin-match"; // Page for mentor-mentee matching
    }

    @PostMapping("/assign")
    public String assignMentorToMentee(@RequestParam Long menteeId, @RequestParam Long mentorId) {
        mentorService.assignMenteeToMentor(menteeId, mentorId);
        return "redirect:/admin/match";
    }

    @GetMapping("/mentors-mentees")
    public String showMentorsAndMentees(Model model) {
        List<Mentor> mentors = mentorService.findAll();
        model.addAttribute("mentors", mentors);
        return "admin-mentors-mentees"; // Page to view mentors and their mentees
    }



}
