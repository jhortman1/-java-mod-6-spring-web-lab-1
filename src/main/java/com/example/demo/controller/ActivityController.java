package com.example.demo.controller;

import com.example.demo.service.ActivityService;
import com.example.demo.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @GetMapping("/activities")
    public List<Activity> readActivity() {
        return activityService.getActivities();
    }
    @DeleteMapping("/activities/{Id}")
    public void deleteActivity(@PathVariable(value = "Id") Integer id) {
        Optional<Activity>activity= Optional.ofNullable(activityService.getActivity(id));
        if(activity.isPresent())
        {
            activityService.deleteActivity(activity.get().getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Activity not found");
        }
    }
    @PostMapping("/activity")
    public ResponseEntity<Activity> createMember(@Valid @RequestBody Activity activity) {
        Activity newActivity = activityService.createActivity(activity);
        return ResponseEntity.ok(newActivity);
    }
}
