package io.paws.paws.controller;

import io.paws.paws.DTO.SigninRequest;
import io.paws.paws.DTO.SignupRequest;
import io.paws.paws.DTO.UserResponse;
import io.paws.paws.repository.UserRepository;
import io.paws.paws.entity.User;
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
    UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/get-me")
    public ResponseEntity<?> getMe(@RequestHeader("Authorization") String token) {
        try {
            // Убираем "Bearer" из строки токена
            String jwtToken = token.substring(7);
            // Получаем email из токена
            String email = jwtService.extractEmail(jwtToken);
            // Находим пользователя по email
            User user = userRepository.findByEmail(email);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
            }
            // Отправляем данные пользователя
            // Вернем данные только те поля, которые хотим показать во фронтенде (name, email, tel, token)
            return ResponseEntity.ok(new UserResponse(user.getName(), user.getEmail(), user.getTel(), user.getToken()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный или истекший токен");
        }
    }

    @PostMapping("/set-me")
    public ResponseEntity<?> setMe(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> updates) {
        try {
            // Убираем "Bearer " из строки токена
            String jwtToken = token.substring(7);
            // Получаем email из токена
            String email = jwtService.extractEmail(jwtToken);
            // Находим пользователя по email
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
            }
            // Обновляем данные пользователя
            if (updates.containsKey("name")) {
                user.setName(updates.get("name"));
            }
            if (updates.containsKey("tel")) {
                user.setTel(Integer.parseInt(updates.get("tel")));
            }
            // Сохраняем обновленные данные
            userRepository.save(user);

            return ResponseEntity.ok("Данные пользователя обновлены");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный или истекший токен");
        }
    }

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

        String token = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok().body(
                Map.of(
                        "token", token,
                        "user", user
                )
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SigninRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный Email или пароль");
        }

        String token = jwtService.generateToken(user.getEmail());
        return ResponseEntity.ok().body(
                Map.of("token", token, "user", user)
        );
    }
}

//@Valid – используется в паре с @RequestBody и гарантирует что в момент вызова
//        метода – будут проверены все аннотации в классе SignupRequest
