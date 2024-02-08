package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {

    @Query("SELECT o from Offer o join fetch o.property p join fetch p.user u where u.email=:email")
    List<Offer> findAllByEmail(String email);

    @Query("SELECT o from Offer o join fetch o.user u where u.email=:email and o.buyerStatus is null")
    List<Offer> findAllByEmailCustomer(String email);


    @Query("Select o from Offer o join fetch o.user u where u.email=:email AND o.buyerStatus is not null")
    List<Offer> getOfferhistory(String email);

}

