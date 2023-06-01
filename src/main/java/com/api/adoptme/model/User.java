package com.api.adoptme.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // specifies that the email field must be unique in the database
    private  String email;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserAnimal> userAnimals;

    public User() {
    }

    public User(Long id, String email, String password, List<UserAnimal> userAnimals) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userAnimals = userAnimals;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAnimal> getUserAnimals() {
        return userAnimals;
    }

    public void setUserAnimals(List<UserAnimal> userAnimals) {
        this.userAnimals = userAnimals;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userAnimals=" + userAnimals +
                '}';
    }
}
