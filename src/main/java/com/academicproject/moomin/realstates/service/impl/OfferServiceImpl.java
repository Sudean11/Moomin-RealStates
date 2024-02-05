package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.repo.OfferRepo;
import com.academicproject.moomin.realstates.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    OfferRepo offerRepo;
    OfferServiceImpl(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }


    @Override
    public void save(Offer offer) {
        offerRepo.save(offer);
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
}
