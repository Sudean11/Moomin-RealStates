package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Location;
import com.academicproject.moomin.realstates.repo.LocationRepo;
import com.academicproject.moomin.realstates.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepo locationRepo;

    @Autowired
    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo= locationRepo;
    }

    @Override
    public void saveLocation(Location location) {
        locationRepo.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepo.findAll();
    }

    @Override
    public Optional<Location> getLocationById(Long id) {
        return locationRepo.findById(id);
    }

    @Override
    public void updateLocation(Long id, Location location) {
        locationRepo.save(location);
    }



}
