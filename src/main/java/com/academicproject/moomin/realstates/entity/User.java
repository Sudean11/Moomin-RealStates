package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "users", schema = "public")
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

    @ManyToMany
    @JoinTable(
            name = "offer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    @Column(name = "status")
    private List<Property> properties;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    @JoinColumn
    @BatchSize(size = 5)
    private List<Offer> offer;
    @ManyToMany
    private List<Favourites> favourites;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}


