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

<<<<<<< Updated upstream
    private String action;

    @ManyToOne
=======
    @ManyToOne
    @JoinColumn(name = "user_id")
>>>>>>> Stashed changes
    private User user;


}
