package com.api.adoptme.controller;

import com.api.adoptme.exception.InformationExistException;
import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.User;
import com.api.adoptme.model.login.LoginRequest;
import com.api.adoptme.model.login.LoginResponse;
import com.api.adoptme.repository.UserRepository;
import com.api.adoptme.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auth")
public class UserController {

    private final UserService userService;

    private HashMap<String, Object> response;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @return
     */
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

    @PostMapping(path = "/users/login")
    // http://localhost:{portNumber}/auth/users/login
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        response = new HashMap<>();
        try {
            LoginResponse loginResponse = userService.loginUser(loginRequest);
            response.put("message", "user logged in");
            response.put("data",loginResponse.getMessage());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (RuntimeException e){
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
