package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.PropertyTypes;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.PropertyRequestDto;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    List<Property> findAll(String type, String area, String zip, String state, String city);

    Optional<Property> findById(Long id);

    void deleteById(Long id);

    void save(PropertyRequestDto property);

    void update(Property property);

    List<Property> getFeaturedProperty();

    List<Property> getRecentProperty();
    List<Property> findByCategory(PropertyTypes category);
    Integer findCount(PropertyTypes category);

}
