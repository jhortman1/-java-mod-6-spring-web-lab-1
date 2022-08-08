package com.example.demo.repository;

import com.example.demo.models.Camper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamperRepository extends JpaRepository<Camper, Long> {
}
