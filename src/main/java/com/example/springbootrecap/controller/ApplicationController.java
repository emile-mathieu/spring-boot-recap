package com.example.springbootrecap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    // This is a simple controller that returns a string when the root URL is accessed
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }
}
