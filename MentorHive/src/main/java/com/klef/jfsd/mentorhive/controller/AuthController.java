package com.klef.jfsd.mentorhive.controller;

import com.klef.jfsd.mentorhive.entity.Admin;
import com.klef.jfsd.mentorhive.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam String email, @RequestParam String password, Model model) {
        Admin admin = adminService.login(email, password);
        if (admin != null) {
        	return "redirect:/admin/dashboard"; // Placeholder for admin dashboard
        } else {
            model.addAttribute("error", "Invalid name or password");
            return "admin-login";
        }
    }
}
