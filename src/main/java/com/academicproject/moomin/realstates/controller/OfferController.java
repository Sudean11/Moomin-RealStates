package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferRequestDTO;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferUpdateDto;
import com.academicproject.moomin.realstates.repo.OfferRepo;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.service.OfferService;
import com.academicproject.moomin.realstates.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/offer")
@CrossOrigin(origins = "*")
public class OfferController {

    OfferService offerService;
    OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // Get all offers
    @GetMapping
    public List<Offer> getAllOffers(@RequestParam(required = false) String email,@RequestParam(required = false) boolean customer ) {
        if(email.isEmpty()){
            return offerService.findAll();
        }else{
            return offerService.findOffersByEmail(email, customer);
        }
    }



    // Get an offer by id
    @GetMapping("/{id}")
    Optional<Offer> getOffer(@PathVariable Long id) {
        return offerService.findById(id);
    }

    @Autowired
    OfferRepo offerRepo;

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    EmailService emailService;
    @PostMapping("/{id}/accept")
    public boolean acceptOffer(@PathVariable long id){
        Offer offer = offerRepo.findById(id).get();
        emailService.sendEmail(offer.getUser().getEmail(), "Offer Acccepted", "Congratulations, your offer has been accepted");
        Property property = offer.getProperty();
        property.setStatus("PENDING");
        offer.setSellerStatus("ACCEPTED");
        offerRepo.save(offer);
        propertyRepo.save(property);
        return true;
    }


    @GetMapping("/history")
    public List<Offer> getOfferHistory(@RequestParam String email){
        return offerService.getOfferHistory(email);
    }

    @PostMapping("/{id}/reject")
    public boolean rejectOffer(@PathVariable long id){
        Offer offer = offerRepo.findById(id).get();
        emailService.sendEmail(offer.getUser().getEmail(), "Offer Rejectd", "The Offer has been rejected");
        Property property = offer.getProperty();
        property.setStatus("AVAILABLE");
        offer.setSellerStatus("REJECTED");
        offerRepo.save(offer);
        propertyRepo.save(property);
        return true;
    }

    @PostMapping("/{id}/customer/accept")
    public boolean acceptCustomerOffer(@PathVariable long id){
        Offer offer = offerRepo.findById(id).get();
        emailService.sendEmail(offer.getProperty().getUser().getEmail(), "Offer Acccepted", "Congratulations, your offer has been accepted");
        Property property = offer.getProperty();
        property.setStatus("CONTINGENT");
        offer.setBuyerStatus("ACCEPTED");
        offerRepo.save(offer);
        propertyRepo.save(property);
        return true;
    }

    @PostMapping("/{id}/customer/reject")
    public boolean rejectCustomerOffer(@PathVariable long id){
        Offer offer = offerRepo.findById(id).get();
        emailService.sendEmail(offer.getProperty().getUser().getEmail(), "Offer Rejecetd", "The offer has been rejecetd");
        Property property = offer.getProperty();
        property.setStatus("AVAILABLE");
        offer.setBuyerStatus("REJECTED");
        propertyRepo.save(property);
        return true;
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

