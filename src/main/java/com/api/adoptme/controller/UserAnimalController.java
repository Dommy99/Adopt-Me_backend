package com.api.adoptme.controller;

import com.api.adoptme.model.Animal;
import com.api.adoptme.model.UserAnimal;
import com.api.adoptme.service.UserAnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class UserAnimalController {

    static HashMap<String, Object> message;

    private final UserAnimalService userAnimalService;

    public UserAnimalController(UserAnimalService userAnimalService) {
        this.userAnimalService = userAnimalService;
    }

    /**
     * Endpoint to retrieve all liked animals for a specific user.
     *
     * @param userId The ID of the user.
     * @return ResponseEntity containing a list of all liked animals by user, and a success message.
     */
    @GetMapping(path = "/user/{userId}/likes")
    public ResponseEntity<?> getLikedAnimalsByUser(@PathVariable Long userId) {
        message = new HashMap<>();
        List<Animal> likedAnimals = userAnimalService.getLikedAnimalsByUser(userId);
        message.put("data", likedAnimals);
        message.put("message", "success");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Endpoint to add an animal to a user's liked list.
     *
     * @param animalId The ID of the animal to like.
     * @return ResponseEntity containing the user-animal relationship, and a success message.
     */
    @PostMapping(path = "/like/{animalId}")
    public ResponseEntity<?> addAnimalToUserLikeList(@PathVariable Long animalId) {
        Map<String, Object> message = new HashMap<>();
        UserAnimal userAnimal = userAnimalService.addAnimalToUserLikeList(animalId);
        message.put("data", userAnimal);
        message.put("message", "success");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }



    /**
     * Endpoint to remove an animal from a user's liked list.
     *
     * @param animalId The ID of the animal to remove from the liked list.
     * @return ResponseEntity containing the removed user-animal relationship, and a success message.
     */
    @DeleteMapping(path = "/like/{animalId}")
    public ResponseEntity<?> deleteAnimeFromUserLikelist(@PathVariable Long animalId) {
        message = new HashMap<>();
        UserAnimal userAnimal = userAnimalService.deleteAnimeFromUserLikelist(animalId);
        message.put("data", userAnimal);
        message.put("message", "delete success");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
