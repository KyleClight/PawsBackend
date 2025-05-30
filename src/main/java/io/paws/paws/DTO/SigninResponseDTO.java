package io.paws.paws.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninResponseDTO {
    private String email;
    private String name;
    private int tel;
    private String imageUrl;
}
