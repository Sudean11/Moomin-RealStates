package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouritesRepo extends JpaRepository<Favourites, Long> {
}
