package com.academicproject.moomin.realstates.entity.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyFetchDTO {
    private String name;
    private double price;
    private Location location;

    @Data
    @AllArgsConstructor
    public static class Location{
         private String address;
         Location(){}
    }
}