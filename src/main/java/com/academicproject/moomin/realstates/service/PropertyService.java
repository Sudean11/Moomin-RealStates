package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    List<Property> findAll();

    Optional<Property> findById(Long id);

    void deleteById(Long id);

    void save(Property property);

    void update(Property property);


}
