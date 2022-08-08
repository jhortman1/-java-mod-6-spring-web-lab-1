package com.example.demo.service;

import com.example.demo.repository.CamperRepository;
import com.example.demo.models.Camper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CamperService {
    @Autowired
    CamperRepository camperRepository;

    public Camper createCamper(Camper camper) {
        camper.setUpdated_at(LocalDateTime.now());
        camper.setCreated_at(LocalDateTime.now());
        return camperRepository.save(camper);
    }

    public List<Camper> getCampers() {
        return camperRepository.findAll();
    }

    public Camper getCamper(Long id) {
        return camperRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Camper not found"));
    }

    public Camper updateCamper(Long id, Camper camperData) {
        Camper camper = camperRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Camper not found"));
        camper.setName(camperData.getName());
        camper.setAge(camperData.getAge());
        camper.setUpdated_at(LocalDateTime.now());
        return camperRepository.save(camper);
    }

    public void deleteCamper(Long id) {
        Camper toBeDeletedCamper = camperRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"error: Camper not found"));
        camperRepository.deleteById(toBeDeletedCamper.getId());
    }
}
