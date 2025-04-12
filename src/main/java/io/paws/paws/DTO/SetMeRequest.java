package io.paws.paws.DTO;

import lombok.Data;

@Data
public class SetMeRequest {
    private String name;
    private String email;
    private String password;
    private String tel;
}
