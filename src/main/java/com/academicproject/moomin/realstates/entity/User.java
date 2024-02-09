package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int user_id;
    @Column(unique = true)
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String contact;
    private String status = "unverified";

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Property> properties;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    @JoinColumn
    @BatchSize(size = 5)
    @JsonIgnore
    private List<Offer> offer;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Favourites> favourites;
//
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "address_id")
    private Location address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<History> history;

    @Override
    public String toString() {
        return "User{" +
                "id=" + user_id +
                ", username='" + email + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}


