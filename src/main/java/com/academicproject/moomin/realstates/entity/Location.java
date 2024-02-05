package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    @JoinColumn(name = "location_id")
    @BatchSize(size = 5)
    private List<Property> properties;
}