package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Location;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.repo.LocationRepo;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public List<Property> findAll(String type, String area, String zip, String state, String city) {
        if (type.equals("") && area.equals("") && state.equals("")) {
            if (!city.equals("") && !zip.equals("") && !state.equals("")) {
                // Case: City, zip, and state are provided, return properties in matching city, zip, and state
                return propertyRepo.findByLocation_CityContainingAndLocation_ZipCodeContainingAndLocation_StateContaining(city, zip, state);
            } else if (!city.equals("") && !zip.equals("")) {
                // Case: Both city and zip are provided, return properties in matching city and zip
                return propertyRepo.findByLocation_CityContainingAndLocation_ZipCodeContaining(city, zip);
            } else if (!city.equals("") && !state.equals("")) {
                // Case: City and state are provided, return properties in matching city and state
                return propertyRepo.findByLocation_CityContainingAndLocation_StateContaining(city, state);
            } else if (!city.equals("") && !area.equals("")) {
                // Case: City and area are provided, return properties in matching city and area
                return propertyRepo.findByLocation_CityContainingAndAreaContaining(city, area);
            } else if (!area.equals("") && !zip.equals("")) {
                // Case: Area and zip are provided, return properties in matching area and zip
                return propertyRepo.findByAreaContainingAndLocation_ZipCodeContaining(area, zip);
            } else if (!type.equals("") && !zip.equals("")) {
                // Case: Type and zip are provided, return properties with matching type and zip
                return propertyRepo.findByTypeContainingAndLocation_ZipCodeContaining(type, zip);
            } else if (!type.equals("") && !state.equals("")) {
                // Case: Type and state are provided, return properties with matching type and state
                return propertyRepo.findByTypeContainingAndLocation_StateContaining(type, state);
            } else if (!area.equals("") && !state.equals("")) {
                // Case: Area and state are provided, return properties with matching area and state
                return propertyRepo.findByAreaContainingAndLocation_StateContaining(area, state);
            } else {
                // Case: All parameters are empty, return all properties
                return propertyRepo.findAll();
            }
        } else if (!type.equals("") && area.equals("") && state.equals("") && zip.equals("")) {
            // Case: Only type is provided, return properties with matching type
            return propertyRepo.findByTypeContaining(type);
        } else if (type.equals("") && !area.equals("") && state.equals("") && zip.equals("")) {
            // Case: Only area is provided, return properties with matching area
            return propertyRepo.findByAreaContaining(area);
        } else if (type.equals("") && area.equals("") && !state.equals("") && zip.equals("")) {
            // Case: Only state is provided, return properties in matching state
            return propertyRepo.findByLocation_StateContaining(state);
        } else if (type.equals("") && area.equals("") && state.equals("") && !city.equals("") && zip.equals("")) {
            // Case: Only city is provided, return properties in matching city
            return propertyRepo.findByLocation_CityContaining(city);
        } else if (type.equals("") && area.equals("") && state.equals("") && city.equals("") && !zip.equals("")) {
            // Case: Only zip is provided, return properties with matching zip code
            return propertyRepo.findByLocation_ZipCodeContaining(zip);
        } else {
            return null;
        }
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




}
