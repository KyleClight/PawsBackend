package io.paws.paws.service;

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
}