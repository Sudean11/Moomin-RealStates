package com.academicproject.moomin.realstates.entity.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDto {
    String email;
    long propertyId;
}
