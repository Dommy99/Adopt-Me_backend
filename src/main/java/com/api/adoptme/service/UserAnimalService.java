package com.api.adoptme.service;

import com.api.adoptme.exception.InformationExistException;
import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.Animal;
import com.api.adoptme.model.User;
import com.api.adoptme.model.UserAnimal;
import com.api.adoptme.repository.UserAnimalRepository;
import com.api.adoptme.security.MyUserDetails;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
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
     * Fetches the Animals that a specific User has liked.
     *
     * @param userId the unique identifier of the User whose liked Animals should be retrieved.
     * @return a List of Animal objects that the User has liked.
     */
    public List<Animal> getLikedAnimalsByUser(Long userId) {
        User user = userService.getUserById(userId);
        List<UserAnimal> userAnimals = userAnimalRepository.findAllByUser(user);
        return userAnimals.stream()
                .map(UserAnimal::getAnimal)
                .collect(Collectors.toList());
    }


    /**
     * Adds a specific Animal to the User's like list.
     *
     * @param animalId the unique identifier of the Animal to add to the User's like list.
     * @return the UserAnimal object representing the User's liking of the Animal.
     * @throws InformationExistException if the Animal is already in the User's like list.
     * @throws AuthenticationCredentialsNotFoundException if the current User is not authenticated.
     */
    public UserAnimal addAnimalToUserLikeList(Long animalId) {
        Animal animal = animalService.getAnimalById(animalId);
        // get currently logged in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof MyUserDetails)) {
            throw new AuthenticationCredentialsNotFoundException("Could not find the current user");
        }
        User user = ((MyUserDetails)principal).getUser();

        UserAnimal userAnimal = userAnimalRepository.findByUserAndAnimal(user, animal);
        if (userAnimal != null) {
            throw new InformationExistException("You have animal with id " + animalId + " in your watchlist.");
        }
        userAnimal = new UserAnimal();
        userAnimal.setUser(user);
        userAnimal.setAnimal(animal);
        userAnimalRepository.save(userAnimal);
        return userAnimal;
    }


    /**
     * Removes a specific Animal from the User's like list.
     *
     * @param animalId the unique identifier of the Animal to remove from the User's like list.
     * @return the UserAnimal object representing the User's liking of the Animal that was removed.
     * @throws InformationNotFoundException if the Animal is not in the User's like list.
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
