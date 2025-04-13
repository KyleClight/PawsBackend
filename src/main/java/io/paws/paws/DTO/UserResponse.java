package io.paws.paws.DTO;

import lombok.Data;
@Data
public class UserResponse {
    private String name;
    private String email;
    private int tel;
    private String token;

    public UserResponse(String name, String email, int tel, String token) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.token = token;
    }
}
