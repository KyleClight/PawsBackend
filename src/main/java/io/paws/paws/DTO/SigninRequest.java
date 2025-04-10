package io.paws.paws.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SigninRequest {
    @Email(message = "Некорректный email")
    @NotBlank (message = "Email обязателен")
        private String email;

    @NotBlank (message = "Пароль обязателен")
        private String password;
}
