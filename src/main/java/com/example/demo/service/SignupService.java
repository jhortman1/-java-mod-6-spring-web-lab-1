package com.example.demo.service;

import com.example.demo.repository.SignupRepository;
import com.example.demo.models.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SignupService {
    @Autowired
    SignupRepository signupRepository;

    public Signup createSignup(Signup signup) {
        return signupRepository.save(signup);
    }

    public List<Signup> getSignups() {
        return signupRepository.findAll();
    }

    public Signup getSignup(Integer id) {
        return signupRepository.findById(id).get();
    }

    public Signup updateSignup(Integer id, Signup signupData) {
        Signup signup = signupRepository.findById(id).get();
        signup.setTime(signup.getTime());
        signup.setUpdated_at(signup.getUpdated_at());
        return signupRepository.save(signup);
    }

    public void deleteSignup(Integer id) {
        signupRepository.deleteById(id);
    }
}
