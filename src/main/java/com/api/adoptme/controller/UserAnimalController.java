package com.api.adoptme.controller;

import com.api.adoptme.model.Animal;
import com.api.adoptme.model.UserAnimal;
import com.api.adoptme.service.UserAnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserAnimalController {

    static HashMap<String, Object> message;

    private UserAnimalService userAnimalService;

    public UserAnimalController(UserAnimalService userAnimalService) {
        this.userAnimalService = userAnimalService;
    }

    /**
     *
     * @param userId
     * @return
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
     *
     * @param animalId
     * @return
     */
    @PostMapping(path = "/like/{animalId}")
    public ResponseEntity<?> addAnimalToUserLikeList(@PathVariable Long animalId) {

        message = new HashMap<>();
        UserAnimal userAnimal = userAnimalService.addAnimalToUserLikeList(animalId);
        message.put("data", userAnimal);
        message.put("message", "success");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     *
     * @param animalId
     * @return
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
