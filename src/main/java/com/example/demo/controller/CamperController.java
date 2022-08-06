package com.example.demo.controller;

import com.example.demo.service.CamperService;
import com.example.demo.models.Camper;
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
public class CamperController {
    @Autowired
    CamperService camperService;

    @GetMapping("/campers")
    public List<Camper> readCampers(){
        return camperService.getCampers();
    }

    @GetMapping("/campers/{camperId}")
    public Camper readCamper(@PathVariable(value = "camperId")Integer id){
        Optional<Camper>camper = Optional.ofNullable(camperService.getCamper(id));
        if(camper.isPresent())
        {
            return camper.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Camper not found");
        }
    }

    @PostMapping("/campers")
    public ResponseEntity<Camper> createCamper(@Valid @RequestBody Camper camper)
    {
        Optional<Camper>newCamper1= Optional.ofNullable(camperService.createCamper(camper));
        if(newCamper1.isPresent())
        {
            return ResponseEntity.ok(newCamper1.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "errors: [validation errors]");
        }
    }
}