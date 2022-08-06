package com.example.demo.service;

import com.example.demo.repository.ActivityRepository;
import com.example.demo.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    ActivityRepository ActivityRepository;

    public Activity createActivity(Activity activity) {
        return ActivityRepository.save(activity);
    }

    public List<Activity> getActivities() {
        return ActivityRepository.findAll();
    }

    public Activity getActivity(Integer id) {
        return ActivityRepository.findById(id).get();
    }

    public Activity updateActivity(Integer id, Activity activityData) {
        Activity activity = ActivityRepository.findById(id).get();
        activity.setName(activityData.getName());
        activity.setDifficulity(activityData.getDifficulity());
        activity.setUpdated_at(activityData.getUpdated_at());
        return ActivityRepository.save(activity);
    }

    public void deleteActivity(Integer id) {
        ActivityRepository.deleteById(id);
    }

}
