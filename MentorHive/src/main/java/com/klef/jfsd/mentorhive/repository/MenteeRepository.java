package com.klef.jfsd.mentorhive.repository;

import com.klef.jfsd.mentorhive.entity.Mentee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Mentee findByEmail(String email); 
    List<Mentee> findByMentorId(Long mentorId);// You can use this to find a mentee by email during login
}
