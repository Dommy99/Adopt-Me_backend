package com.api.adoptme.repository;

import com.api.adoptme.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository  extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);
    Optional<Animal> findById(Long id);
}
