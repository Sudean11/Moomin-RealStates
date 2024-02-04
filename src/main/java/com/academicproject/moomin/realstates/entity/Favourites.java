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
    @OneToOne(mappedBy = "user_id")
    private User user;
    @OneToMany()
    @Column
    private List<Property> property;
}
