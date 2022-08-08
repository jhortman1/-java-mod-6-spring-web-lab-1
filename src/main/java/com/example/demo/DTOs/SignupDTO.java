package com.example.demo.DTOs;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SignupDTO {
    private int id;
    private int time;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}