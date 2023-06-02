package com.api.adoptme.seed;

import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.Animal;
import com.api.adoptme.model.User;
import com.api.adoptme.repository.AnimalRepository;
import com.api.adoptme.repository.UserAnimalRepository;
import com.api.adoptme.repository.UserRepository;
import com.api.adoptme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    
    private final UserService userService;
    
    private final AnimalRepository animalRepository;
    
    private final UserAnimalRepository userAnimalRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, UserService userService, AnimalRepository animalRepository, UserAnimalRepository userAnimalRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.animalRepository = animalRepository;
        this.userAnimalRepository = userAnimalRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadAnimalData();
        LoadUserAnimalData();
    }

    private User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new InformationNotFoundException("Not Found!");
        }
    }

    private Animal getAnimal(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if (animal.isPresent()) {
            return animal.get();
        } else {
            throw new InformationNotFoundException("Not Found!");
        }
    }

    private void LoadUserAnimalData() {
    }

    private void loadAnimalData() {
    }

    private void loadUserData() {
    }
}
