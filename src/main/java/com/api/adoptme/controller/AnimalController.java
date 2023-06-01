package com.api.adoptme.controller;

import com.api.adoptme.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping(path = "/api")
public class AnimalController {

    static HashMap<String, Object>message;

    @Autowired
    private AnimalService animalService;

}
