package io.paws.paws.service;

import io.paws.paws.DTO.PetCardDTO;
import io.paws.paws.entity.Pets;
import io.paws.paws.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetsService {
    private final PetRepository petRepository;

    public void deletePet (String id) {
        petRepository.deleteById(id);
    }
    public Pets save(Pets pet) {
        return petRepository.save(pet);
    }
    public List<Pets> getAllPets() {
        return petRepository.findAll();
    }
    public Optional<Pets> getPetById(String id) {
        return petRepository.findById(id);
    }

    public Pets changePet(String id, Pets updatedPet) {
        Pets existingPet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Питомец не найден"));

        existingPet.setName(updatedPet.getName());
        existingPet.setType(updatedPet.getType());
        existingPet.setBreed(updatedPet.getBreed());
        existingPet.setChipNumber(updatedPet.getChipNumber());
        existingPet.setImageUrl(updatedPet.getImageUrl());
        existingPet.setBirthDate(updatedPet.getBirthDate());
        existingPet.setSex(updatedPet.getSex());
        existingPet.setVaccine(updatedPet.getVaccine());
        return petRepository.save(existingPet);
    }


    private PetCardDTO convertToDTO(Pets pet) {
        return new PetCardDTO(
                pet.getId(),
                pet.getName(),
                pet.getType(),
                pet.getBreed(),
                pet.getChipNumber(),
                pet.getImageUrl(),
                pet.getBirthDate(),
                pet.getSex().name(), //.name() – преобразование enum в String
                pet.getVaccine(),
                pet.getMedication(),

                //STATUS
                pet.getLastFeed(),
                pet.getLastWalk(),
                pet.getLastFeed()
        );
    }

    public List<PetCardDTO> getAllPetCards() {
        return petRepository.findAll().stream()          // 1. Получаем всех питомцев и создаем поток (Stream)
                .map(this::convertToDTO)                     // 2. Каждый объект Pets преобразуем в PetCardDTO
                .toList();                                   // 3. Собираем результат в список
    }
}