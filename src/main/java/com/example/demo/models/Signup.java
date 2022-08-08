package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Signup {
    @Id
    @GeneratedValue
    private int id;
//    private int camper_id;
//    private int activity_id;
    @Min(value = 0)
    @Max(23)
    private int time;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @ManyToOne
    private Activity activity;
    @ManyToOne
    private Camper camper;
}
