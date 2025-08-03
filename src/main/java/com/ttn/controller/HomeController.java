package com.ttn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/admin")
    public String about() {
        return "This is an Admin Page!";
    }

    @GetMapping("/user")
    public ResponseEntity<String> userPage() {
        return ResponseEntity.ok("This is a User Page!");
    }
}
