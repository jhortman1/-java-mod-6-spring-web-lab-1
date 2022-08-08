package com.example.demo.DTOs;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
public class CamperDTO {
    private Long id;
    private String name;
    private int age;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private List<SignupDTO> signups = new ArrayList<>();
}
