package com.api.adoptme.controller;

import com.api.adoptme.exception.InformationExistException;
import com.api.adoptme.model.User;
import com.api.adoptme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

    private final UserService userService;

    private HashMap<String, Object> response;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    // http://localhost:{portNumber}/auth/users
    public ResponseEntity<?> getAllUsers(){
        response = new HashMap<>();
        List<User> users = userService.getAllUsers();
        if (users != null) {
            response.put("message","success");
            response.put("data", users);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            response.put("message", "Insufficient rights");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

    /**
     *
     * @param userObj
     * @return
     */
    @PostMapping(path = "/users/register")
    // http://localhost:{portNumber}/auth/users/register
    public ResponseEntity<?> createUser(@RequestBody User userObj){
        response = new HashMap<>();
        try {
            User newUser = userService.createUser(userObj);
            response.put("message","user created");
            response.put("data",newUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (InformationExistException e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.CONFLICT);
        }
    }
}
