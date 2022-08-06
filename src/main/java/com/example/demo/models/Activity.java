package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Activity {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank
    private String name;
    private int difficulity;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @OneToMany(mappedBy = "activity_id", cascade = CascadeType.REMOVE)
    private List<Signup> signups = new ArrayList<>();
}
