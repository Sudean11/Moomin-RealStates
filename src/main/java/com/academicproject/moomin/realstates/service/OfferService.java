package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Offer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OfferService {
    void save(Offer offer);
    void deleteById(Long id);
   Optional<Offer> findById(Long id);
   List<Offer> findAll();
    void update(Long id, Offer offer);
}
