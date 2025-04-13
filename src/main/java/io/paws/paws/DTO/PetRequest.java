package io.paws.paws.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetRequest {
    private String name;
    private String type;
    private String breed;
    private String sex; // строка: male/female
    private int age;
    private LocalDate birthdate;
    private boolean medication;
}
