package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
