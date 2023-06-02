package com.api.adoptme.service;

import com.api.adoptme.exception.InformationExistException;
import com.api.adoptme.exception.InformationNotFoundException;
import com.api.adoptme.model.User;
import com.api.adoptme.model.login.LoginRequest;
import com.api.adoptme.model.login.LoginResponse;
import com.api.adoptme.repository.UserRepository;
import com.api.adoptme.security.JWTUtils;
import com.api.adoptme.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private JWTUtils jwtUtils;

    private MyUserDetails myUserDetails;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       @Lazy AuthenticationManager authenticationManager,JWTUtils jwtUtils, @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.myUserDetails = myUserDetails;
    }



    public User createUser(User userObject) {
        Optional<User> user = userRepository.findUserByEmail(userObject.getEmail());
        if (user.isEmpty()) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }
        throw new InformationExistException("User with email " + userObject.getEmail() + " already exists.");
    }

    public User findUserByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        throw new InformationNotFoundException("User with email "+ email+ " was not found.");
    }

//    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
//        try {
//            // Authenticate the user with the provided email and password
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//            // Set the authenticated user in the security context
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            // Get the authenticated user details
//            myUserDetails = (MyUserDetails) authentication.getPrincipal();
//
//            // Generate a JWT token for the authenticated user
//            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
//            // Return the generated JWT token in the response
//            return ResponseEntity.ok(new LoginResponse(JWT));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Error: email or password is incorrect"));
//        }
//    }

public LoginResponse loginUser(LoginRequest loginRequest){
    try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        myUserDetails = (MyUserDetails) authentication.getPrincipal();
        final String jwtToken = jwtUtils.generateJwtToken(myUserDetails);
        return new LoginResponse(jwtToken);
    }catch (Exception e)
    {
        throw new RuntimeException("Email or Password is incorrect.");
    }
}


}
