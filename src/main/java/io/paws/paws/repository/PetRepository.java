package io.paws.paws.repository;

import io.paws.paws.entity.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pets, String> {
    Pets id(String id);

    Pets name(String name);
}
