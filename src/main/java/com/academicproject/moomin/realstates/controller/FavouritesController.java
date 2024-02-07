package com.academicproject.moomin.realstates.controller;


import com.academicproject.moomin.realstates.entity.Favourites;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.FavouritesRequestDto;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.FavouritesService;
import com.academicproject.moomin.realstates.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/favourites")
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


    @Autowired
    UserRepo userRepo;
    @Autowired
    PropertyRepo propertyRepo;

    @PostMapping
    public void saveFavourite(@RequestBody FavouritesRequestDto favouritesRequestDto) {
        Optional<User> user = userRepo.findById(favouritesRequestDto.getUserId());
        Optional<Property> property = propertyRepo.findById(favouritesRequestDto.getPropertyId());


        Favourites favourites = new Favourites();
        favourites.setUser(user.get());
        favourites.setProperty(property.get());

       favouritesService.saveFavourite(favourites);

    }


}
}
