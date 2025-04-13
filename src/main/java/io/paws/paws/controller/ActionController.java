package io.paws.paws.controller;

import io.paws.paws.entity.Pets;
import io.paws.paws.repository.PetsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
    @RequestMapping("/action")
    public class ActionController {
    
    final
    PetsRepository petsRepository;

    public ActionController(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

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
