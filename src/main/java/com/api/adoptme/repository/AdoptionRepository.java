package com.api.adoptme.repository;

import com.api.adoptme.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Optional<Adoption> findByName(String name);
}
