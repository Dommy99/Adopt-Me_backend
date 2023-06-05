package com.api.adoptme.service;

import com.api.adoptme.exception.InformationExistException;
import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.Animal;
import com.api.adoptme.model.User;
import com.api.adoptme.model.UserAnimal;
import com.api.adoptme.repository.UserAnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAnimalService {

    private AnimalService animalService;

    private UserService userService;
    private UserAnimalRepository userAnimalRepository;

    public UserAnimalService(AnimalService animalService, UserService userService, UserAnimalRepository userAnimalRepository) {
        this.animalService = animalService;
        this.userService = userService;
        this.userAnimalRepository = userAnimalRepository;
    }

    /**
     *
     * @param userId
     * @return
     */
    public List<Animal> getLikedAnimalsByUser(Long userId) {
        User user = userService.getUserById(userId);
        List<UserAnimal> userAnimals = userAnimalRepository.findAllByUser(user);
        return userAnimals.stream()
                .map(UserAnimal::getAnimal)
                .collect(Collectors.toList());
    }


    /**
     *
     * @param animalId
     * @return
     */
    public UserAnimal addAnimalToUserLikeList(Long animalId) {
        Animal animal = animalService.getAnimalById(animalId);
        UserAnimal userAnimal = userAnimalRepository.findByUserAndAnimal(AnimalService.getCurrentLoggedInUser(), animal);
        if (userAnimal != null) {
            throw new InformationExistException("You have animal with id " + animalId + " in your watchlist.");
        }
        userAnimal = new UserAnimal();
        userAnimal.setUser(AnimalService.getCurrentLoggedInUser());
        userAnimal.setAnimal(animal);
         userAnimalRepository.save(userAnimal);
        return userAnimal;
    }

    /**
     *
     * @param animalId
     * @return
     */
    public UserAnimal deleteAnimeFromUserLikelist(Long animalId) {
        Animal animal = animalService.getAnimalById(animalId);
        UserAnimal userAnimal = userAnimalRepository.findByUserAndAnimal(AnimalService.getCurrentLoggedInUser(), animal);
        if (userAnimal != null) {
            userAnimalRepository.delete(userAnimal);
            return userAnimal;
        } else {
            throw new InformationNotFoundException("Animal with id " + animalId + "isn't in your likelist");
        }
    }
}
