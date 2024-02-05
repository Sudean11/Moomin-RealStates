package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {


}
