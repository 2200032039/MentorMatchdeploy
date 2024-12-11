package com.klef.jfsd.mentorhive.service.impl;

import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.entity.Notification;
import com.klef.jfsd.mentorhive.repository.MenteeRepository;
import com.klef.jfsd.mentorhive.repository.MentorRepository;
import com.klef.jfsd.mentorhive.repository.NotificationRepository;
import com.klef.jfsd.mentorhive.service.MentorService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
@Service
public class MentorServiceImpl implements MentorService {

    
    
    @Autowired
    private MenteeRepository menteeRepository;

   
    
    @Override
    public List<Mentor> findMentorsByStatus(String status) {
        return mentorRepository.findByStatus(status);
    }

    @Override
    public void updateMentorStatus(Long id, String status) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new RuntimeException("Mentor not found"));
        mentor.setStatus(status);
        mentor.setUpdatedAt(LocalDate.now());
        mentorRepository.save(mentor);
    }
    
    @Override
    public long countMentorsByStatus(String status) {
        long count = mentorRepository.countByStatus(status);
        System.out.println("Count for status " + status + ": " + count);  // Log the count
        return count;
    }
    
    @Override
    public Mentor findMentorById(Long id) {
        return mentorRepository.findById(id).orElseThrow(() -> new RuntimeException("Mentor not found"));
    }

    public Mentor findMentorByEmailAndPassword(String email, String password) {
        return mentorRepository.findByEmailAndPassword(email, password).orElse(null);
    }
    
    public Mentor findMentorByEmail(String email) {
        return mentorRepository.findByEmail(email);
    }

   

    public List<Mentor> searchMentors(String specialisation, String location, String skills) {
        return mentorRepository.findMentorsByCriteria(
            specialisation != null && !specialisation.isEmpty() ? specialisation : null,
            location != null && !location.isEmpty() ? location : null,
            skills != null && !skills.isEmpty() ? skills : null
        );
    }

    public List<Mentor> findAllApprovedMentors() {
        return mentorRepository.findByStatus("Approved");
    }
    
    
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public Mentor getMentorById(Long mentorId) {
        return mentorRepository.findById(mentorId).orElse(null);
    }

    public void saveMentor(Mentor mentor) {
        mentorRepository.save(mentor);
    }

    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }
    
    @Override
    public List<Mentor> findAvailableMentors() {
        return mentorRepository.findByAvailabilityGreaterThan(0); // Assumes 'availability' tracks remaining slots
    }

    @Override
    public void assignMenteeToMentor(Long menteeId, Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId)
                        .orElseThrow(() -> new IllegalArgumentException("Mentor not found"));
        Mentee mentee = menteeRepository.findById(menteeId)
                        .orElseThrow(() -> new IllegalArgumentException("Mentee not found"));

        // Update the relationship
        mentee.setMentor(mentor);
        mentor.setAvailability(mentor.getAvailability() - 1); // Decrease availability

        menteeRepository.save(mentee);
        mentorRepository.save(mentor);
    }

    @Override
    public List<Mentee> findMenteesByMentorId(Long mentorId) {
        return menteeRepository.findByMentorId(mentorId);
    }

	@Override
	public List<Mentor> findAll() {
		// TODO Auto-generated method stub
		return mentorRepository.findAll();
	}

	@Override
	public List<Mentee> getMenteesByMentorId(Long id) {
	
		return menteeRepository.findByMentorId(id);
	}

	
	
    

}
