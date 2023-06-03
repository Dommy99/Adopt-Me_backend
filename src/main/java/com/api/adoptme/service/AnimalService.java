package com.api.adoptme.service;

import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.Animal;
import com.api.adoptme.model.User;
import com.api.adoptme.repository.AnimalRepository;
import com.api.adoptme.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }


    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    };

    public Animal getAnimalById(Long animalId) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (animal.isEmpty()) {
            throw new InformationNotFoundException("Animal with id " + animalId + "not found");
        } else {
            return animal.get();
        }
    }
}
