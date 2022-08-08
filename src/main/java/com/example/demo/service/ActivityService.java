package com.example.demo.service;

import com.example.demo.repository.ActivityRepository;
import com.example.demo.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository ActivityRepository;

    public Activity createActivity(Activity activity) {
        activity.setCreated_at(LocalDateTime.now());
        activity.setUpdated_at(LocalDateTime.now());
        return ActivityRepository.save(activity);
    }

    public List<Activity> getActivities() {
        return ActivityRepository.findAll();
    }

    public Activity getActivity(Integer id) {
        return ActivityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Activity updateActivity(Integer id, Activity activityData) {
        Activity activity = ActivityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        activity.setName(activityData.getName());
        activity.setDifficulity(activityData.getDifficulity());
        activity.setUpdated_at(LocalDateTime.now());
        return ActivityRepository.save(activity);
    }

    public void deleteActivity(Integer id) {
        Activity toBeDeletedActivity = ActivityRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        ActivityRepository.deleteById(toBeDeletedActivity.getId());
    }

}
