package io.paws.paws.controller;

import io.paws.paws.DTO.SigninRequest;
import io.paws.paws.DTO.SignupRequest;
import io.paws.paws.repository.UserRepository;
import io.paws.paws.entity.User;
import io.paws.paws.repository.PetsRepository;
import io.paws.paws.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    PetsRepository petsRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
        // Проверка: если пользователь с таким email уже есть
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Пользователь с таким email уже существует");
        }


        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Пользователь успешно зарегистрирован");

    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный Email или пароль");
        }
        String token = jwtService.generateToken(user.getEmail());
        user.setToken(token);
        userRepository.save(user);
        return ResponseEntity.ok().body(
                Map.of(
                        "token", token,
                        "user", user
                )
        );
    }
}

//@Valid – используется в паре с @RequestBody и гарантирует что в момент вызова
//        метода – будут проверены все аннотации в классе SignupRequest
