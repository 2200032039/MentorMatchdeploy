package com.klef.jfsd.mentorhive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.mentorhive.entity.Course;
import com.klef.jfsd.mentorhive.entity.Mentee;
import com.klef.jfsd.mentorhive.entity.Mentor;

public interface CourseRepository extends  JpaRepository<Course, Long>{

	List<Course> findByMentorId(Long mentorId);

	List<Course> findByMenteesContains(Mentee mentee);

	List<Course> findByMentor(Mentor mentor);
}
