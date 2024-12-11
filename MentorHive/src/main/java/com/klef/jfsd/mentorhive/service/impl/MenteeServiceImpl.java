package com.klef.jfsd.mentorhive.service.impl;

import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.repository.MenteeRepository;
import com.klef.jfsd.mentorhive.service.MenteeService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenteeServiceImpl implements MenteeService {

    @Autowired
    private MenteeRepository menteeRepository;

   
 
    		@Override
    		public void saveMentee(Mentee mentee) {
    		    try {
    		        System.out.println("Saving mentee: " + mentee.getName());
    		        menteeRepository.save(mentee);
    		    } catch (Exception e) {
    		        System.err.println("Error saving mentee: " + e.getMessage());
    		        e.printStackTrace(); // Logs the full stack trace to identify issues
    		    }
    		}

    		public Mentee findByEmail(String email) {
    		    return menteeRepository.findByEmail(email);
    		}
     
    		
    		 public Mentee getMenteeById(Long mentorId) {
    		        return menteeRepository.findById(mentorId).orElse(null);
    		    }


		
}
