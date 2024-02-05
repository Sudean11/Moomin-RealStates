package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Favourites;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavouritesService {
    Favourites saveFavourite(Favourites favourites);
    List<Favourites> getAllFavourites();
    Favourites getFavouriteById(Long id);
    void deleteFavourite(Long id);
}