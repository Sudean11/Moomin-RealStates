package com.academicproject.moomin.realstates.service;

import org.springframework.stereotype.Service;

@Service
public interface OfferSerice {
    void save();
    void update();
    void deleteById();
    void findById();
    void findAll();
}
