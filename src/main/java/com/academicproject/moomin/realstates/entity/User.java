package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int user_id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String contact;
    private String address;
    @OneToMany
    private List<Property> property;
    @OneToOne
    private  Offer offer;


}


