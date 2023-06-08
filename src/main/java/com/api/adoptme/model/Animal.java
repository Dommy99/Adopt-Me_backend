package com.api.adoptme.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

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

    @Column
    private String img;

    @JsonBackReference
    @OneToMany(mappedBy = "animal")
    private List<UserAnimal> userAnimals;


    @ManyToOne
    @JoinColumn(name = "adoption_id")
    private Adoption adoption;
    public Animal() {
    }

    public Animal(String name, String gender, String color, String age, String breed, String species, String img, Adoption adoption) {
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.age = age;
        this.breed = breed;
        this.species = species;
        this.img = img;
        this.adoption = adoption;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<UserAnimal> getUserAnimals() {
        return userAnimals;
    }

    public void setUserAnimals(List<UserAnimal> userAnimals) {
        this.userAnimals = userAnimals;
    }

    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
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
                ", img='" + img + '\'' +
                ", userAnimals=" + userAnimals +
                ", adoption=" + adoption +
                '}';
    }

}
