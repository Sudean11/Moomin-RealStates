package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "history")
@Data

public class History {
    @Id
    @GeneratedValue
    private Long id;

}
