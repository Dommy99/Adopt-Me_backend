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
}
