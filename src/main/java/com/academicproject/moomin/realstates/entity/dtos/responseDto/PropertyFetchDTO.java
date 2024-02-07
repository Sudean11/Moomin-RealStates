package com.academicproject.moomin.realstates.entity.dtos.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyFetchDTO {
    private Long id;
    private String name;
    private double price;
    private Location location;
    private String banner;
    private String propertyImages;
    private String area;
    private String status;
    private String description;
    private String owner;
    private Integer bathroom;
    private Integer bedroom;


    @Data
    @AllArgsConstructor
    public static class Location{
         private String address;
         Location(){}
    }
}