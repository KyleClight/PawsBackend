package io.paws.paws.service;

import io.paws.paws.entity.Pets;
import io.paws.paws.repository.PetsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service //говорим spring что это сервисный класс
public class PetsService {
    private final PetsRepository petsRepository;
    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    public Pets save(Pets pet) {
        return petsRepository.save(pet);
    }
    public List<Pets> getAllAPets() {
        return petsRepository.findAll();
    }
    public Optional<Pets> getPetById(Long id) {
        return petsRepository.findById(id);
    }

    public void deletePet(Long id) {
        petsRepository.deleteById(id);
    }
}
