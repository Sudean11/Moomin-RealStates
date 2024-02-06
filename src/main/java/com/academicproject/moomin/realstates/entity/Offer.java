package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private Property property;

    @Column(name = "status")
    private String status;

    @Column(name = "buyerstatus")
    private String buyerStatus;

    @Column(name = "sellerstatus")
    private String sellerStatus;

    @OneToMany(mappedBy = "offer", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Message> messages;

    public String getStatus() {
        if (property != null) {
            return property.getStatus();
        }
        return status;
    }
}
