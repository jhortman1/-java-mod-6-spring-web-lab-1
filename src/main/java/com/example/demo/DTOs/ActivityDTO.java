package com.example.demo.DTOs;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ActivityDTO {
    private int id;
    private String name;
    private int difficulity;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
