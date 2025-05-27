package io.paws.paws.repository;

import io.paws.paws.entity.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pets, String> {
    Pets id(String id);

    Pets name(String name);
}
