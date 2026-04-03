package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> userMap){
        String email = userMap.get("email");
        String password = userMap.get("password");

        String message = authService.register(email, password);
        Map<String,String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String> userMap){
        String email = userMap.get("email");
        String password = userMap.get("password");

        String token = authService.login(email, password);
        Map<String,String> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Login successful");
        return response;
    }
}