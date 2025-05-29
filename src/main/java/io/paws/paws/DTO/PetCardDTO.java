package io.paws.paws.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetCardDTO {
    private String id;
    private String name;
    private String type;
    private String breed;
    private String chipNumber;
    private String imageUrl;
    private String birthDate;
    private String sex;
    private boolean vaccine;
    private boolean medication;

    //STATUS
    private String lastFeed;
    private String lastWalk;
    private String lastMedication;
}
