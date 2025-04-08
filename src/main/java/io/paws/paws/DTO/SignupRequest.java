package io.paws.paws.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Email(message = "Email должен быть правильного формата")
    @NotBlank(message = "Email не может быть пустым")
    private String email;
    private String password;

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }
}


//@NotBlank – проверяет, что поле ввода не пустое
//@Email – проверяет что значение соответствует формату Email
