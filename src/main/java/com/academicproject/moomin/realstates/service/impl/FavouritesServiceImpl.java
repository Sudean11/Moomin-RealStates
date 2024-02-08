package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Favourites;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.FavouriteDto;
import com.academicproject.moomin.realstates.repo.FavouritesRepo;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    private final FavouritesRepo favouritesRepo;

    @Autowired
    public FavouritesServiceImpl(FavouritesRepo favouritesRepo) {
        this.favouritesRepo = favouritesRepo;
    }

    @Autowired
    UserRepo userRepo;

    @Autowired
    PropertyRepo propertyRepo;

    @Override
    public Favourites saveFavourite(FavouriteDto favourites) {
        User user = userRepo.findByEmail(favourites.getEmail());
        Property property = propertyRepo.findById(favourites.getPropertyId()).get();
        Favourites favourites1 = new Favourites();
        favourites1.setUser(user);
        favourites1.setProperty(property);
        return favouritesRepo.save(favourites1);
    }

    @Override
    public List<Favourites> getAllFavourites() {
        return favouritesRepo.findAll();
    }

    @Override
    public Favourites getFavouriteById(Long id) {
        return favouritesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Favourite not found for this id :: " + id));
    }


    @Override
    public void deleteFavourite(Long id) {
        Favourites favourites = getFavouriteById(id);
        favouritesRepo.delete(favourites);
    }
}
