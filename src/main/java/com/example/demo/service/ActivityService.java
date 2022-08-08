package com.example.demo.service;

import com.example.demo.models.Signup;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.models.Activity;
import com.example.demo.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    SignupRepository signupRepository;

    public Activity createActivity(Activity activity) {
        activity.setCreated_at(LocalDateTime.now());
        activity.setUpdated_at(LocalDateTime.now());
        return activityRepository.save(activity);
    }

    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivity(Long id) {
        return activityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Activity not found"));
    }

    public Activity updateActivity(Long id, Activity activityData) {
        Activity activity = activityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Activity not found"));
        activity.setName(activityData.getName());
        activity.setDifficulity(activityData.getDifficulity());
        activity.setUpdated_at(LocalDateTime.now());
        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        Activity toBeDeletedActivity = activityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Activity not found"));
        activityRepository.delete(toBeDeletedActivity);
    }

}
