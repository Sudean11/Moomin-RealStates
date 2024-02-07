package com.academicproject.moomin.realstates.entity.dtos.requestDto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavouritesRequestDto {



        private int userId;
        private Long propertyId;
}

