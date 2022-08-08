package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Camper {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @Min(value = 8)
    @Max(18)
    private int age;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @OneToMany(mappedBy = "camper", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Signup> signups = new ArrayList<>();
}
