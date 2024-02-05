package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private String type;
    private String area;
    private String status;
    private String description;
    private String owner;

    @ManyToOne()
    @JsonBackReference
    private Location location;

    @ManyToMany(mappedBy = "properties")
    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @JsonManagedReference
    @JoinColumn
    @BatchSize(size = 5)
    private List<Offer> offers;
}


