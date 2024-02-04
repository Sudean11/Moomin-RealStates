package com.academicproject.moomin.realstates.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends JpaRepository<OfferRepo, Long> {
}
