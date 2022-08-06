package com.example.demo.controller;

import com.example.demo.service.SignupService;
import com.example.demo.models.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SignupController {
    @Autowired
    SignupService signupService;
    @PostMapping("/signup")
    public ResponseEntity<Signup> createCamper(@Valid @RequestBody Signup signup)
    {
        Optional<Signup> newSignup = Optional.ofNullable(signupService.createSignup(signup));
        if(newSignup.isPresent())
        {
            return ResponseEntity.ok(newSignup.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "errors: [validation errors]");
        }
    }
}

