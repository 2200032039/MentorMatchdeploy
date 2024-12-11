package com.klef.jfsd.mentorhive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This will map to home.html
    }

    @GetMapping("/admin/login")
    public String showAdminLoginPage() {
        return "admin-login"; // This will map to admin-login.html
    }
}
