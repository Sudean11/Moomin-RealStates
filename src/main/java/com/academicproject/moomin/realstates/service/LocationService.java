package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LocationService {
    void saveLocation(Location location);
    List<Location> getAllLocations();
    Optional<Location> getLocationById(Long id);
   void updateLocation(Long id, Location locationDetails);

}
