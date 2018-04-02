package com.example.backendusers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public String findAllUsers() {
        return "It's working!!!";
    }

}
