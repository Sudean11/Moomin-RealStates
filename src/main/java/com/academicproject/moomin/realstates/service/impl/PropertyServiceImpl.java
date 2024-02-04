package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    PropertyRepo propertyRepo;
    PropertyServiceImpl(PropertyRepo propertyRepo){
        this.propertyRepo = propertyRepo;
    }


    @Override
    public List<Property> findAll() {
        return  propertyRepo.findAll();
    }

    @Override
    public Optional<Property> findById(Long id) {
        return  propertyRepo.findById(id);

    }

    @Override
    public void deleteById(Long id) {
        propertyRepo.deleteById(id);

    }

    @Override
    public void save(Property property) {
        propertyRepo.save(property);

    }

    @Override
    public void update(Property property) {
        propertyRepo.save(property);

    }




}
