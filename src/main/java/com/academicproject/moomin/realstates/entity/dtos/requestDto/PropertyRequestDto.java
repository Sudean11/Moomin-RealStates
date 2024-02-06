package com.academicproject.moomin.realstates.entity.dtos.requestDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PropertyRequestDto {
    private String name;
    private String address;
    private String phoneNumber;
    private MultipartFile bannerImage;
    private List<MultipartFile> propertyImages;
}
