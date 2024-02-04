package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String address;
    @OneToMany
    @Column(name = "property_id")
    private List<Property> property;



}
