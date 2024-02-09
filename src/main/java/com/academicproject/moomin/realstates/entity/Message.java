package com.academicproject.moomin.realstates.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sender_id;
    private String message;
    private String email;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonBackReference
    private Offer offer;
}
