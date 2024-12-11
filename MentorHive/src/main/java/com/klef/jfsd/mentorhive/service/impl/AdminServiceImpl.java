package com.klef.jfsd.mentorhive.service.impl;

import com.klef.jfsd.mentorhive.entity.Admin;
import com.klef.jfsd.mentorhive.repository.AdminRepository;
import com.klef.jfsd.mentorhive.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
