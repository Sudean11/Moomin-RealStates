package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private String area;
    private String status;
    private String description;
    private String owner;
    private Integer bathroom;
    private Integer bedroom;

    @Enumerated(EnumType.STRING)
    private PropertyTypes propertyTypes;

    private boolean featured;


    @ManyToOne()
    @JsonBackReference
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "property")
    private List<Offer> offers;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
    private List<Favourites> favourites;

    @OneToOne
    private Image banner;

    @OneToMany
    private List<Image> propertyImages;

    public void setBanner(byte[] imageData) {
        Image image = new Image();
        image.setData(imageData);
        this.banner = image;
    }
}


