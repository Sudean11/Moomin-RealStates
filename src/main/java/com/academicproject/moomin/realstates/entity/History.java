package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "history")
@Data

public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String action;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
