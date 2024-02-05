package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Favourites {
    @GeneratedValue
    @Id
    private Long id;
    @OneToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @Column
    private List<Property> property;
}
