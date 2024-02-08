package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferRequestDTO;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.OfferUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OfferService {
    void save(OfferRequestDTO offer);
    void deleteById(Long id);
   Optional<Offer> findById(Long id);
   List<Offer> findAll();
    void update(Long id, Offer offer);

    List<Offer> findOffersByEmail(String email, boolean customer);
    public void partialUpdate(Long id, OfferUpdateDto partialOffer);

    List<Offer> getOfferHistory(String email);
}
