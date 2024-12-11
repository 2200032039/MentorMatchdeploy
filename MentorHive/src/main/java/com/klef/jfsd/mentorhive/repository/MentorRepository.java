package com.klef.jfsd.mentorhive.repository;

import com.klef.jfsd.mentorhive.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Mentor findByEmail(String email);
    List<Mentor> findByStatus(String status);
    long countByStatus(String status);
    
    @Query("SELECT COUNT(m) FROM Mentor m WHERE m.status = :status")
    long countMentorsByStatus(@Param("status") String status);
    Optional<Mentor> findByEmailAndPassword(String email, String password);
    @Query("SELECT m FROM Mentor m WHERE "
    	       + "(:specialisation IS NULL OR m.specialisation = :specialisation) AND "
    	       + "(:location IS NULL OR m.location = :location) AND "
    	       + "(:skills IS NULL OR m.skills LIKE %:skills%) AND "
    	       + "m.status = 'Approved'")
    	List<Mentor> findMentorsByCriteria(@Param("specialisation") String specialisation,
    	                                   @Param("location") String location,
    	                                   @Param("skills") String skills);
	List<Mentor> findByAvailabilityGreaterThan(int i);

	List<Mentor> findAll();


   
 

}
