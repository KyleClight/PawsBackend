package io.paws.paws.controller;

import io.paws.paws.DTO.PetActionRequest;
import io.paws.paws.entity.Pets;
import io.paws.paws.repository.PetsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/action")
public class ActionController {

    final
    PetsRepository petsRepository;

    public ActionController(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    @PostMapping("/feed/{id}")
    public ResponseEntity<String> feedPet(@PathVariable long id, @RequestBody PetActionRequest request) {
        Pets pet = petsRepository.findById(id).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(404).body("Питомец не найден");
        }

        LocalDateTime feedTime = Instant.ofEpochSecond(request.getTimestamp())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        pet.setLastFeed(feedTime);  // Обновляем время кормления
        petsRepository.save(pet);
        return ResponseEntity.ok("Питомец накормлен");
    }
    // Эндпоинт для прогулки с питомцем
    @PostMapping("/walk/{id}")
    public ResponseEntity<String> walkPet(@PathVariable long id, @RequestBody PetActionRequest request) {
        Pets pet = petsRepository.findById(id).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(404).body("Питомец не найден");
        }

        LocalDateTime walkTime = Instant.ofEpochSecond(request.getTimestamp())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        pet.setLastWalk(walkTime);  // Обновляем время прогулки
        petsRepository.save(pet);
        return ResponseEntity.ok("Питомец выгулен");
    }

    // Эндпоинт для медикаментов питомцу
    @PostMapping("/medication/{id}")
    public ResponseEntity<String> medicatePet(@PathVariable long id, @RequestBody PetActionRequest request) {
        Pets pet = petsRepository.findById(id).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(404).body("Питомец не найден");
        }

        LocalDateTime medicationTime = Instant.ofEpochSecond(request.getTimestamp())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        pet.setLastMedication(medicationTime);  // Обновляем время принятия медикаментов
        petsRepository.save(pet);
        return ResponseEntity.ok("Питомец получил медикаменты");
    }

    // Эндпоинт для получения информации о питомце по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable long id) {
        return petsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}