package com.example.demo.service;

import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.CamperRepository;
import com.example.demo.repository.SignupRepository;
import com.example.demo.models.Signup;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class SignupService {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    SignupRepository signupRepository;

    @Autowired
    CamperRepository camperRepository;

    public Signup createSignup(Signup signup) {
        signup.setCreated_at(LocalDateTime.now());
        signup.setUpdated_at(LocalDateTime.now());
        return signupRepository.save(signup);
    }

    public List<Signup> getSignups() {
        return signupRepository.findAll();
    }

    public Signup getSignup(Long id) {
        return signupRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Signup not found"));
    }

    public Signup updateSignup(Long id, Signup signupData) {
        Signup signup = signupRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Signup not found"));
        signup.setTime(signup.getTime());
        signup.setUpdated_at(LocalDateTime.now());
        return signupRepository.save(signup);
    }

    public void deleteSignup(Long id) {
        Signup toBeDeletedSignup = signupRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Signup not found"));
        signupRepository.deleteById(toBeDeletedSignup.getId());
    }
}
