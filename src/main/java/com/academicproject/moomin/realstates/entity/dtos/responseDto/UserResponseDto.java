package com.academicproject.moomin.realstates.entity.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    long id;
    String firstname;
    String lastname;
}
