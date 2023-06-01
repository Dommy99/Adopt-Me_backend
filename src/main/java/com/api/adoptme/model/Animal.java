package com.api.adoptme.model;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private String color;

    @Column
    private String age;

    @Column
    private String breed;

    @Column
    private String species;

    public Animal() {
    }

    public Animal(Long id, String name, String gender, String color, String age, String breed, String species) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.breed = breed;
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", color='" + color + '\'' +
                ", age='" + age + '\'' +
                ", breed='" + breed + '\'' +
                ", species='" + species + '\'' +
                '}';
    }
}
