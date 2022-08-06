package com.example.demo.service;

import com.example.demo.repository.CamperRepository;
import com.example.demo.models.Camper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CamperService {
    @Autowired
    CamperRepository camperRepository;

    public Camper createCamper(Camper camper) {
        return camperRepository.save(camper);
    }

    public List<Camper> getCampers() {
        return camperRepository.findAll();
    }

    public Camper getCamper(Integer id) {
        return camperRepository.findById(id).get();
    }

    public Camper updateCamper(Integer id, Camper camperData) {
        Camper camper = camperRepository.findById(id).get();
        camper.setName(camperData.getName());
        camper.setAge(camperData.getAge());
        camper.setUpdated_at(camperData.getUpdated_at());
        return camperRepository.save(camper);
    }

    public void deleteCamper(Integer id) {
        camperRepository.deleteById(id);
    }
}
