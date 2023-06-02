package com.api.adoptme.seed;

import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.Animal;
import com.api.adoptme.model.User;
import com.api.adoptme.model.UserAnimal;
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
        if (userAnimalRepository.count() == 0){
            User user1 = getUser(1L);
            User user2 = getUser(2L);
            User user3 = getUser(3L);

            Animal animal1 = getAnimal(1L);
            Animal animal2 = getAnimal(2L);
            Animal animal3 = getAnimal(3L);

            UserAnimal userAnimal1 = new UserAnimal(user1,animal1);
            UserAnimal userAnimal2 = new UserAnimal(user2,animal2);
            UserAnimal userAnimal3 = new UserAnimal(user3,animal3);

            userAnimalRepository.save(userAnimal1);
            userAnimalRepository.save(userAnimal2);
            userAnimalRepository.save(userAnimal3);
        }
    }

    private void loadAnimalData() {
        if (animalRepository.count() == 0) {
            Animal animal1 = new Animal("Tim","male","brown","2","boxer","dog");
            Animal animal2 = new Animal("Timmy","male","black","3","pitbull","dog");
            Animal animal3 = new Animal("Tims","female","orange","4","siamese","cat");
            animalRepository.save(animal1);
            animalRepository.save(animal2);
            animalRepository.save(animal3);
        }
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User("123@email.com","123");
            User user2 = new User("1234@email.com","1234");
            User user3 = new User("12345@email.com","12345");
            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
        }
    }
}
