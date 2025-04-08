package io.paws.paws.controller;

import io.paws.paws.DTO.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok("Пользователь: " + request.getName()+ ", email:" + request.getEmail());

    }

}

//@Valid – используется в паре с @RequestBody и гарантирует что в момент вызова
//        метода – будут проверены все аннотации в классе SignupRequest
