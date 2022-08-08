package com.example.demo.controller;

import com.example.demo.DTOs.SignupDTO;
import com.example.demo.service.SignupService;
import com.example.demo.models.Signup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SignupController {
    @Autowired
    SignupService signupService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/getSignups")
    public List<SignupDTO> readSignups(){
        return signupService.getSignups().stream().map(signup -> modelMapper.map(signup, SignupDTO.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/getSignup/{signupId}")
    public ResponseEntity<SignupDTO> readSignup(@PathVariable(value = "signupId")Integer id){
        Signup signup = signupService.getSignup(id);
        SignupDTO signupResponse = modelMapper.map(signup,SignupDTO.class);
        return ResponseEntity.ok().body(signupResponse);
    }

    @PostMapping("/creteSignup")
    public ResponseEntity<SignupDTO> createSignup(@Valid @RequestBody SignupDTO signup)
    {
        Signup signupRequest = modelMapper.map(signup,Signup.class);
        Signup newSignup = signupService.createSignup(signupRequest);
        SignupDTO signupResponse = modelMapper.map(newSignup,SignupDTO.class);
        return new ResponseEntity<>(signupResponse,HttpStatus.CREATED);
    }
    @PutMapping("/updateSignup/{signupId}")
    public ResponseEntity<SignupDTO> updateSignup (@PathVariable(value = "signupId")Integer id,@RequestBody SignupDTO signupDTO)
    {
        Signup signupRequest = modelMapper.map(signupDTO,Signup.class);
        Signup newSignup = signupService.updateSignup(id,signupRequest);
        SignupDTO signupResponse = modelMapper.map(newSignup,SignupDTO.class);
        return new ResponseEntity<>(signupResponse,HttpStatus.CREATED);
    }
    @DeleteMapping("deleteSignup/{signupId}")
    public void deleteSignup(@PathVariable(name = "id") Integer id)
    {
        signupService.deleteSignup(id);
    }
}

