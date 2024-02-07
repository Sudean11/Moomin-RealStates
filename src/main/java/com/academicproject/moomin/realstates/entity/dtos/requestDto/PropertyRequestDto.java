package com.academicproject.moomin.realstates.entity.dtos.requestDto;

import com.academicproject.moomin.realstates.entity.Location;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PropertyRequestDto {
    private MultipartFile banner;
    private List<MultipartFile> propertyImages;
    private String name;
    private int price;
    private String area;
    private String status;
    private String description;
    private String owner;
    private Integer bathroom;
    private Integer bedroom;
    private Location location;
}
