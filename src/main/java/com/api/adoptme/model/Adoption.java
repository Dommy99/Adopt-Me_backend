package com.api.adoptme.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "adoption_agency")
public class Adoption {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String location;

    @Column
    private String number;

    @Column(unique = true)
    private String email;

    @Column
    private String name;

    @Column
    private String website;

    @JsonBackReference
    @OneToMany(mappedBy = "adoption")
    private List<Animal> animals;
    public Adoption() {
    }

    public Adoption(String location, String number, String email, String name, String website) {
        this.location = location;
        this.number = number;
        this.email = email;
        this.name = name;
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", animals=" + animals +
                '}';
    }


//    public Adoption(Long id, String location, String number, String email, String name, List<Animal> animals) {
//        this.id = id;
//        this.location = location;
//        this.number = number;
//        this.email = email;
//        this.name = name;
//        this.animals = animals;
//    }

//    public Adoption( String location, String number, String email, String name) {
//        this.location = location;
//        this.number = number;
//        this.email = email;
//        this.name = name;
//    }


}
