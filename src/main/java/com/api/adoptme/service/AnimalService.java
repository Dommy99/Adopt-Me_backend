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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails userDetails) {
            return userDetails.getUser();
        }
        throw new IllegalArgumentException("User is not authenticated");
    }


    /**
     * Fetches all Animals from the AnimalRepository.
     *
     * @return a List of all Animal objects present in the AnimalRepository.
     */
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    };

    /**
     * Retrieves a specific Animal from the AnimalRepository by its id.
     *
     * @param animalId the unique identifier of the Animal object to retrieve.
     * @return the Animal object with the specified id.
     * @throws InformationNotFoundException if the Animal object with the specified id is not found.
     */
    public Animal getAnimalById(Long animalId) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (animal.isEmpty()) {
            throw new InformationNotFoundException("Animal with id " + animalId + "not found");
        } else {
            return animal.get();
        }
    }
}
