package com.portfolio.naeim.controller;

import com.portfolio.naeim.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @GetMapping("/api/user")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(new User("john_doe", "john@example.com"), HttpStatus.OK);
    }
}
