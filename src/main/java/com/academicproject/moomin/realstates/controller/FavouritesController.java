package com.academicproject.moomin.realstates.controller;


import com.academicproject.moomin.realstates.entity.Favourites;
import com.academicproject.moomin.realstates.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourites")
@CrossOrigin(origins = "*")
public class FavouritesController {

    private final FavouritesService favouritesService;

    @Autowired
    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @PostMapping
    public Favourites createFavourite(@RequestBody Favourites favourites) {
        return favouritesService.saveFavourite(favourites);
    }

    @GetMapping
    public List<Favourites> getAllFavourites() {
        return favouritesService.getAllFavourites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favourites> getFavouriteById(@PathVariable Long id) {
        Favourites favourites = favouritesService.getFavouriteById(id);
        return ResponseEntity.ok().body(favourites);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavourite(@PathVariable Long id) {
        favouritesService.deleteFavourite(id);
        return ResponseEntity.noContent().build();
    }
}
