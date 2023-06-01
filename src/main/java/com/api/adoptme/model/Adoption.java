package com.api.adoptme.model;

import javax.persistence.*;

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

    public Adoption() {
    }

    public Adoption(Long id, String location, String number, String email, String name) {
        this.id = id;
        this.location = location;
        this.number = number;
        this.email = email;
        this.name = name;
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

    @Override
    public String toString() {
        return "Adoption{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
