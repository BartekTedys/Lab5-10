package com.example.Lab5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.Lab5")  // Adjust the base package as needed
public class Lab5Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lab5Application.class, args);
    }

}
