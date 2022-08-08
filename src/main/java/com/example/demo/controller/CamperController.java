package com.example.demo.controller;

import com.example.demo.models.DTOs.CamperDTO;
import com.example.demo.service.CamperService;
import com.example.demo.models.Camper;
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
public class CamperController {
    @Autowired
    CamperService camperService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/campers")
    public List<CamperDTO> readCampers(){
        return camperService.getCampers().stream().map(camper -> modelMapper.map(camper,CamperDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/campers/{camperId}")
    public ResponseEntity<CamperDTO> readCamper(@PathVariable(value = "camperId")Integer id){
        Camper camper = camperService.getCamper(id);
        CamperDTO camperResponse = modelMapper.map(camper,CamperDTO.class);
        return ResponseEntity.ok().body(camperResponse);
    }

    @PostMapping("/campers")
    public ResponseEntity<CamperDTO> createCamper(@Valid @RequestBody CamperDTO camper)
    {
        Camper camperRequest = modelMapper.map(camper,Camper.class);
        Camper newCamper = camperService.createCamper(camperRequest);
        CamperDTO camperResponse = modelMapper.map(newCamper,CamperDTO.class);
        return new ResponseEntity<>(camperResponse,HttpStatus.CREATED);
    }
    @PutMapping("/campers/{camperId}")
    public ResponseEntity<CamperDTO> updateCamper (@PathVariable(value = "camperId")Integer id,@RequestBody CamperDTO camperDTO)
    {
        Camper camperRequest = modelMapper.map(camperDTO,Camper.class);
        Camper newCamper = camperService.updateCamper(id,camperRequest);
        CamperDTO camperResponse = modelMapper.map(newCamper,CamperDTO.class);
        return new ResponseEntity<>(camperResponse,HttpStatus.CREATED);
    }
    @DeleteMapping("campers/{camperId}")
    public void deleteCamper(@PathVariable(name = "id") Integer id)
    {
        camperService.deleteCamper(id);
    }
}