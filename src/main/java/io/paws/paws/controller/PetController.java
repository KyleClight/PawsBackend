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
@RequestMapping("/pet")
public class PetController {

    final
    PetsRepository petsRepository;

    public PetController(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    @PostMapping("/feed/{id}")
    public ResponseEntity<String> feedPet(@PathVariable long id, @RequestBody PetActionRequest request) {
        Pets pet = petsRepository.findById(id).orElse(null);
        if (pet == null) {
            return ResponseEntity.status(404).body("Питомец не найден");
        }

        String feedTime = Instant.ofEpochSecond(request.getTimestamp()).toString();

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

        String walkTime = Instant.ofEpochSecond(request.getTimestamp()).toString();

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

        String medicationTime = Instant.ofEpochSecond(request.getTimestamp()).toString();

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

    // Эндпоинт для получения питомца по id
    @GetMapping("/all")
    public ResponseEntity<?> getAllPets() {
        return ResponseEntity.ok(petsRepository.findAll());
    }

    @PostMapping("/create-new")
    public ResponseEntity<Pets> createPet(@RequestBody Pets request) {
        request.setId(null); // На случай если фронт случайно прислал id
        request.setLastFeed("");
        request.setLastWalk("");
        request.setLastMedication("");
        Pets savedPet = petsRepository.save(request);
        return ResponseEntity.ok(savedPet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePet(@PathVariable long id) {
        return petsRepository.findById(id)
                .map(pet -> {
                    petsRepository.delete(pet);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.status(404).body("Питомец не найден"));
    }
}