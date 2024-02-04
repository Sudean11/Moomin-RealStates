package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

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
    @ManyToOne
   private Location location;
   @ManyToOne
   private User user;
   @OneToMany
   private List<Offer> offer;




}
