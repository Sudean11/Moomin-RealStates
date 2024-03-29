package com.academicproject.moomin.realstates.entity.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequestDTO {
    private String email;
    private long propertyId;
    private String buyerStatus;
    private double price;
    private String message;
}