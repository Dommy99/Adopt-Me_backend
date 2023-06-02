package com.api.adoptme.model;

import javax.persistence.*;

@Entity
@Table(name = "user_animals")
public class UserAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public UserAnimal() {
    }

//    public UserAnimal(Long id, User user, Animal animal) {
//        this.id = id;
//        this.user = user;
//        this.animal = animal;
//    }

    public UserAnimal( User user, Animal animal) {
        this.user = user;
        this.animal = animal;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "UserAnimal{" +
                "id=" + id +
                ", user=" + user +
                ", animal=" + animal +
                '}';
    }
}
