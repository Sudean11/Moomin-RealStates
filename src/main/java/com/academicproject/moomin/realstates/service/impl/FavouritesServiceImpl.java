package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Favourites;
import com.academicproject.moomin.realstates.repo.FavouritesRepo;
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

    @Override
    public Favourites saveFavourite(Favourites favourites) {
        return favouritesRepo.save(favourites);
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
