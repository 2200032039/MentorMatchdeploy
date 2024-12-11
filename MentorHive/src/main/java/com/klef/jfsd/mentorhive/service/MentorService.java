package com.klef.jfsd.mentorhive.service;

import java.util.List;

import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;
import com.klef.jfsd.mentorhive.entity.Notification;

import jakarta.servlet.http.HttpSession;

public interface MentorService {
    void saveMentor(Mentor mentor);
    List<Mentor> findMentorsByStatus(String status);
    void updateMentorStatus(Long id, String status);
    long countMentorsByStatus(String status);
	Mentor findMentorById(Long id);
	 Mentor findMentorByEmailAndPassword(String email, String password);
	 Mentor findMentorByEmail(String email);
	 List<Mentor> searchMentors(String specialisation, String location, String skills);
	 //List<Mentor> findAllMentors();
	 List<Mentor> findAllApprovedMentors();
	 Mentor getMentorById(Long mentorId);
	 void saveNotification(Notification notification) ;
	 
		    List<Mentor> findAvailableMentors();
		    void assignMenteeToMentor(Long menteeId, Long mentorId);
		    List<Mentee> findMenteesByMentorId(Long mentorId);
			List<Mentor> findAll();
			List<Mentee> getMenteesByMentorId(Long id);
		

	 

}
