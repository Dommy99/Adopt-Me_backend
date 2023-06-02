package com.api.adoptme.security;

import com.api.adoptme.model.User;
import com.api.adoptme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    /**
     * Loads the UserDetails object for a given email.
     *
     * @param email the email of the user to load
     * @return the UserDetails object for the given email
     * @throws UsernameNotFoundException if the email is not found in the system
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        return new MyUserDetails(user);
    }

}
