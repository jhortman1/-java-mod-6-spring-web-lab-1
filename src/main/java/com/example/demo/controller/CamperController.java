package com.example.demo.controller;

import com.example.demo.DTOs.CamperDTO;
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

    @GetMapping("/getCampers")
    public List<CamperDTO> readCampers(){
        return camperService.getCampers().stream().map(camper -> modelMapper.map(camper,CamperDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/getCampers/{camperId}")
    public ResponseEntity<CamperDTO> readCamper(@PathVariable(value = "camperId")Long id){
        Camper camper = camperService.getCamper(id);
        CamperDTO camperResponse = modelMapper.map(camper,CamperDTO.class);
        return ResponseEntity.ok().body(camperResponse);
    }

    @PostMapping("/createCampers")
    public ResponseEntity<CamperDTO> createCamper(@Valid @RequestBody CamperDTO camper)
    {
        Camper camperRequest = modelMapper.map(camper,Camper.class);
        Camper newCamper = camperService.createCamper(camperRequest);
        CamperDTO camperResponse = modelMapper.map(newCamper,CamperDTO.class);
        return new ResponseEntity<>(camperResponse,HttpStatus.CREATED);
    }
    @PutMapping("/updateCampers/{camperId}")
    public ResponseEntity<CamperDTO> updateCamper (@PathVariable(value = "camperId")Long id,@RequestBody CamperDTO camperDTO)
    {
        Camper camperRequest = modelMapper.map(camperDTO,Camper.class);
        Camper newCamper = camperService.updateCamper(id,camperRequest);
        CamperDTO camperResponse = modelMapper.map(newCamper,CamperDTO.class);
        return new ResponseEntity<>(camperResponse,HttpStatus.CREATED);
    }
    @DeleteMapping("deleteCamper/{camperId}")
    public void deleteCamper(@PathVariable(name = "camperId") Long id)
    {
        camperService.deleteCamper(id);
    }
}