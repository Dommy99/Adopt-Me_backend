package com.api.adoptme.repository;

import com.api.adoptme.model.Animal;
import com.api.adoptme.model.User;
import com.api.adoptme.model.UserAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnimalRepository extends JpaRepository<UserAnimal, Long> {
    UserAnimal findByUserAndAnimal(User user, Animal animal);
}
