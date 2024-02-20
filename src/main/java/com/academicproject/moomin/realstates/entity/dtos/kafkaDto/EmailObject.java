package com.academicproject.moomin.realstates.entity.dtos.kafkaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailObject {
    String name;
    String email;
    String description;
}
