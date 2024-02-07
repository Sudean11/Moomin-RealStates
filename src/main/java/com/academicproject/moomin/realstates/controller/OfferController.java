package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferRequestDTO;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferUpdateDto;
import com.academicproject.moomin.realstates.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/offer")
@CrossOrigin("*")
public class OfferController {

    OfferService offerService;
    OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // Get all offers
    @GetMapping
    public List<Offer> getAllOffers() {
       return offerService.findAll();
    }

    // Get an offer by id
    @GetMapping("/{id}")
    Optional<Offer> getOffer(@PathVariable Long id) {
        return offerService.findById(id);
    }

    // Create a new offer
    @PostMapping
    public void postOffer(@RequestBody OfferRequestDTO offer) {
         offerService.save(offer);
    }

    // Update an existing offer
    @PutMapping("/{id}")
    public void putOffer(@PathVariable Long id, @RequestBody Offer offer) {

       offerService.update(id, offer);
    }

    // Delete an offer
    @DeleteMapping("/{id}")
    public void  deleteOffer(@PathVariable Long id) {
        offerService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void patchOffer(@PathVariable Long id, @RequestBody OfferUpdateDto offerUpdates) {
        offerService.partialUpdate(id, offerUpdates);
    }
}

