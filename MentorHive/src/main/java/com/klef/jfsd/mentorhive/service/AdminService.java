package com.klef.jfsd.mentorhive.service;

import com.klef.jfsd.mentorhive.entity.Admin;

public interface AdminService {
    Admin login(String email, String password);
}
