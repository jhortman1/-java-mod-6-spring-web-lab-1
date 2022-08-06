package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Signup {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Camper camper_id;
    @ManyToOne
    private Activity activity_id;
    @Min(value = 0)
    @Max(23)
    private int time;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
