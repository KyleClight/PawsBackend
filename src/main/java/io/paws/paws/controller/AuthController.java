package io.paws.paws.controller;

import io.paws.paws.DTO.SigninRequest;
import io.paws.paws.DTO.SignupRequest;
import io.paws.paws.entity.Pets;
import io.paws.paws.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PetsRepository petsRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.ok("Пользователь: " + request.getName()+ ", email: " + request.getEmail());
    }
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody @Valid SigninRequest request) {

        if (request.getEmail() != null
                && request.getEmail().isEmpty()
                && request.getPassword() != null
                && request.getPassword().isEmpty()){
        return  ResponseEntity.ok("Вход выполнен успешно для: " + request.getEmail());
        } else {
            return ResponseEntity.status(401).body("Неверный Email или пароль");
        }
    }

    @RestController
    @RequestMapping("/action")
    public class Action{
        @PostMapping("/feed/{id}")
        public ResponseEntity<String> feedPet(@PathVariable long id) {
            Pets pet = petsRepository.findById(id).orElse(null);
            if (pet == null) {
                return ResponseEntity.status(404).body("Питомец не найден");
            }
            pet.setLastFeed(LocalDateTime.now());  // Обновляем время кормления
            petsRepository.save(pet);
            return ResponseEntity.ok("Питомец накормлен");
        }
        // Эндпоинт для прогулки с питомцем
        @PostMapping("/walk/{id}")
        public ResponseEntity<String> walkPet(@PathVariable long id) {
            Pets pet = petsRepository.findById(id).orElse(null);
            if (pet == null) {
                return ResponseEntity.status(404).body("Питомец не найден");
            }
            pet.setLastWalk(LocalDateTime.now());  // Обновляем время прогулки
            petsRepository.save(pet);
            return ResponseEntity.ok("Питомец выгулен");
        }

        // Эндпоинт для медикаментов питомцу
        @PostMapping("/medication/{id}")
        public ResponseEntity<String> medicatePet(@PathVariable long id) {
            Pets pet = petsRepository.findById(id).orElse(null);
            if (pet == null) {
                return ResponseEntity.status(404).body("Питомец не найден");
            }
            pet.setLastMedication(LocalDateTime.now());  // Обновляем время принятия медикаментов
            petsRepository.save(pet);
            return ResponseEntity.ok("Питомец получил медикаменты");
        }
    }
}

//@Valid – используется в паре с @RequestBody и гарантирует что в момент вызова
//        метода – будут проверены все аннотации в классе SignupRequest
