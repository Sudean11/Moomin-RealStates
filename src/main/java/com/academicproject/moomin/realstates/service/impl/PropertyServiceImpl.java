package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Location;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyFetchDTO;
import com.academicproject.moomin.realstates.helper.ListMapper;
import com.academicproject.moomin.realstates.repo.LocationRepo;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    LocationRepo locationRepo;
    PropertyServiceImpl(PropertyRepo propertyRepo){
        this.propertyRepo = propertyRepo;
    }


    public List<Property> findAll(String type, String area, String zip, String state, String city) {
        Specification<Property> specification = Specification.where(null);

        if (!type.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("type"), "%" + type + "%"));
        }

        if (!area.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("area"), "%" + area + "%"));
        }

        if (!zip.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("location").get("zipCode"), "%" + zip + "%"));
        }

        if (!state.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("location").get("state"), "%" + state + "%"));
        }

        if (!city.isEmpty()) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("location").get("city"), "%" + city + "%"));
            System.out.println(specification);

        }


        return propertyRepo.findAll(specification);
    }



    @Override
    public Optional<Property> findById(Long id) {
        return  propertyRepo.findById(id);

    }

    @Override
    public void deleteById(Long id) {
        Optional<Property> propertyOptional = propertyRepo.findById(id);
        propertyOptional.ifPresent(property -> {
            Location location = property.getLocation();
            if (location != null) {
                location.getProperties().remove(property);
                locationRepo.save(location);
            }
            propertyRepo.deleteById(id);
        });
    }

    @Override
    public void save(Property property) {
        Location location = property.getLocation();

        // Check if the Location is not null and hasn't been saved yet
        if (location != null && location.getId() == null) {
            // Save the Location first
            locationRepo.save(location);
        }

        // Now you can save the Property
        propertyRepo.save(property);

    }

    @Override
    public void update(Property property) {
        propertyRepo.save(property);

    }


    @Override
    public List<Property> getFeaturedProperty() {
        return propertyRepo.getFeaturedProperty();
    }

    @Override
    public List<Property> getRecentProperty() {
        return propertyRepo.findLast8AddedProperties((Pageable) PageRequest.of(0, 8));
    }

    @Autowired
    ListMapper listMapper;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<PropertyFetchDTO> getTest() {
        List<Property> a = propertyRepo.getTest();
        List<PropertyFetchDTO> p = listMapper.mapList(a, new PropertyFetchDTO());
        return p;
    }


}
