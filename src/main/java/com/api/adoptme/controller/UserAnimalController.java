package com.api.adoptme.controller;

import com.api.adoptme.model.UserAnimal;
import com.api.adoptme.service.UserAnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api")
public class UserAnimalController {

    static HashMap<String, Object> message;

    private UserAnimalService userAnimalService;

    public UserAnimalController(UserAnimalService userAnimalService) {
        this.userAnimalService = userAnimalService;
    }

    @PostMapping(path = "/user/{userId}/like/{animalId}")
    public ResponseEntity<?> addAnimalToUserLikeList(@PathVariable Long animalId) {

        message = new HashMap<>();
        UserAnimal userAnimal = userAnimalService.addAnimalToUserLikeList(animalId);
        message.put("data", userAnimal);
        message.put("message", "success");
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
