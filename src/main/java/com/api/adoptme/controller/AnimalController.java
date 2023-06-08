package com.api.adoptme.controller;

import com.api.adoptme.model.Animal;
import com.api.adoptme.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class AnimalController {

    static HashMap<String, Object>message;

    @Autowired
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Endpoint to retrieve all animals.
     *
     * @return ResponseEntity containing a list of all animals, and a success message.
     */
    @GetMapping(path = "/animal/")
    public ResponseEntity<?> getAllAnimals(){
        message = new HashMap<>();
        List<Animal> animalList = animalService.getAllAnimals();
        message.put("message", "success");
        message.put("data", animalList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
