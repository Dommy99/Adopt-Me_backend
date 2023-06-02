package com.api.adoptme.controller;

import com.api.adoptme.exception.InformationExistException;
import com.api.adoptme.model.User;
import com.api.adoptme.model.login.LoginRequest;
import com.api.adoptme.model.login.LoginResponse;
import com.api.adoptme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

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
     * @param userObj
     * @return
     */
    @PostMapping(path = "/users/register")
    // http://localhost:{portNumber}/auth/users/register
    public User createUser(@RequestBody User userObj){
        return userService.createUser(userObj);
    }

    @PostMapping(path = "/users/login")
    // http://localhost:{portNumber}/auth/users/login
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
