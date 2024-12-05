package com.example.Lab5.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "newPassword123"; // Your new password
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Hashed Password: " + encodedPassword);
    }
}
