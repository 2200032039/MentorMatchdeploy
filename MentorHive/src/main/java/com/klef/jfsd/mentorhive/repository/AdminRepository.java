package com.klef.jfsd.mentorhive.repository;

import com.klef.jfsd.mentorhive.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
