package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferRequestDTO;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferUpdateDto;
import com.academicproject.moomin.realstates.repo.OfferRepo;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    OfferRepo offerRepo;
    @Autowired
    UserRepo userRepo;
    OfferServiceImpl(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void save(OfferRequestDTO offer) {
        User user = userRepo.findById(offer.getUserId()).get();
        Property property = propertyRepo.findById(offer.getPropertyId()).get();
        Offer finalOffer = modelMapper.map(offer, Offer.class);
        finalOffer.setMessages(offer.getMessage());
        finalOffer.setUser(user);
        finalOffer.setProperty(property);
        offerRepo.save(finalOffer);
    }

    @Override
    public void deleteById(Long id) {
        offerRepo.deleteById(id);

    }

    @Override
    public Optional<Offer> findById(Long id) {
        return offerRepo.findById(id);
    }

    @Override
    public List<Offer> findAll() {
        return offerRepo.findAll();
    }

    @Override
    public void update(Long id, Offer offer) {
        offerRepo.save(offer);
    }

    @Override
    public void partialUpdate(Long id, OfferUpdateDto partialOffer) {
        Optional<Offer> optionalOffer = offerRepo.findById(id);
        if (optionalOffer.isPresent()) {
            Offer existingOffer = optionalOffer.get();
            if (partialOffer.getBuyerStatus() != null) {
                existingOffer.setBuyerStatus(partialOffer.getBuyerStatus());
            }
            if (partialOffer.getSellerStatus() != null) {
                existingOffer.setSellerStatus(partialOffer.getSellerStatus());
            }
            offerRepo.save(existingOffer);
        } else {
            throw new NoSuchElementException("Offer with ID " + id + " not found");
        }
    }
}
