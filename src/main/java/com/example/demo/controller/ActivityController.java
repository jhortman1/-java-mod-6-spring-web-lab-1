package com.example.demo.controller;

import com.example.demo.DTOs.ActivityDTO;
import com.example.demo.service.ActivityService;
import com.example.demo.models.Activity;
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
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/getActivities")
    public List<ActivityDTO> readActivities(){
        return activityService.getActivities().stream().map(activity -> modelMapper.map(activity,ActivityDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getActivity/{activityId}")
    public ResponseEntity<ActivityDTO> readActivity(@PathVariable(value = "activityId")Integer id){
        Activity activity = activityService.getActivity(id);
        ActivityDTO activityResponse = modelMapper.map(activity,ActivityDTO.class);
        return ResponseEntity.ok().body(activityResponse);
    }

    @PostMapping("/CreateActivity")
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activity)
    {
        Activity activityRequest = modelMapper.map(activity,Activity.class);
        Activity newActivity = activityService.createActivity(activityRequest);
        ActivityDTO activityResponse = modelMapper.map(newActivity,ActivityDTO.class);
        return new ResponseEntity<>(activityResponse,HttpStatus.CREATED);
    }
    @PutMapping("/updateActivities/{activityId}")
    public ResponseEntity<ActivityDTO> updateActivity (@PathVariable(value = "activityId")Integer id,@RequestBody ActivityDTO activityDTO)
    {
        Activity activityRequest = modelMapper.map(activityDTO,Activity.class);
        Activity newActivity = activityService.updateActivity(id,activityRequest);
        ActivityDTO activityResponse = modelMapper.map(newActivity,ActivityDTO.class);
        return new ResponseEntity<>(activityResponse,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("deleteActivity/{activityId}")
    public void deleteActivity(@PathVariable(name = "activityId") Integer id)
    {
        activityService.deleteActivity(id);
    }
}
