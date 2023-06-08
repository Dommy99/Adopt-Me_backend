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
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    private MyUserDetails myUserDetails;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       @Lazy AuthenticationManager authenticationManager, JWTUtils jwtUtils, @Lazy MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.myUserDetails = myUserDetails;
    }

    /**
     * Creates a new User in the UserRepository.
     *
     * @param userObject the User object to create in the UserRepository.
     * @return the created User object.
     * @throws InformationExistException if a User with the same email as the User object already exists.
     */
    public User createUser(User userObject) {
        Optional<User> user = userRepository.findUserByEmail(userObject.getEmail());
        if (user.isEmpty()) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        }
        throw new InformationExistException("User with email " + userObject.getEmail() + " already exists.");
    }

    /**
     * Finds a specific User in the UserRepository by their email.
     *
     * @param email the email of the User to find.
     * @return the User object with the specified email.
     * @throws InformationNotFoundException if the User with the specified email is not found.
     */
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new InformationNotFoundException("User with email " + email + " was not found.");
    }

    /**
     * Logs in a User by authenticating their email and password.
     *
     * @param loginRequest the LoginRequest object containing the User's email and password.
     * @return a LoginResponse object containing the JWT token and the User's id.
     * @throws RuntimeException if the email or password is incorrect.
     */
    public LoginResponse loginUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails = (MyUserDetails) authentication.getPrincipal();
            final String jwtToken = jwtUtils.generateJwtToken(myUserDetails);
            Long userId = myUserDetails.getUser().getId();
            return new LoginResponse(jwtToken, userId);
        } catch (Exception e) {
            throw new RuntimeException("Email or Password is incorrect.");
        }
    }


    /**
     * Retrieves a specific User from the UserRepository by their id.
     *
     * @param userId the unique identifier of the User to retrieve.
     * @return the User object with the specified id.
     * @throws InformationNotFoundException if the User with the specified id is not found.
     */
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findUserById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new InformationNotFoundException("User with id " + userId + " was not found.");
    }
}
