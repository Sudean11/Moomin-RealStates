package com.academicproject.moomin.realstates.entity.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyCountDTO {

    private String propertyType;
    private Long count;


    // Getters and setters
}