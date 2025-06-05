package io.paws.paws.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name="pets")
public class Pets {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(updatable = false, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    public Sex sex;

    private String name;
    private String type;
    private String breed;
    private String chipNumber;
    private String imageUrl;
    private String birthDate;
    private int age;
    private boolean vaccine;
    private boolean medication;

    //STATUS
    private String lastFeed;
    private String lastWalk;
    private String lastMedication;

    public enum Sex {
        Male, Female
    }

    public boolean getVaccine() {
        return vaccine;
    }

    public boolean getMedication() {
        return medication;
    }
}
