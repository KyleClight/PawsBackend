package io.paws.paws.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="pets")
public class Pets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String type;
    private String breed;
    private String chipNumber;
    private String imageUrl;
    private String birthDate;
    private int age;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private boolean vaccine;
    private boolean medication;
    //Текущее состояние животного
    private String lastFeed;
    private String lastWalk;
    private String lastMedication;

    private enum Sex {
        Male, Female
    }

    public boolean getVaccine() {
        return vaccine;
    }
}
