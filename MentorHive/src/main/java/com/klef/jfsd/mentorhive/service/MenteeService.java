package com.klef.jfsd.mentorhive.service;

import java.util.List;

import com.klef.jfsd.mentorhive.entity.Mentee;


import jakarta.transaction.Transactional;
@Transactional
public interface MenteeService {
    void saveMentee(Mentee mentee);

	Mentee findByEmail(String email);
	
	Mentee getMenteeById(Long mentorId);

	

    
}
