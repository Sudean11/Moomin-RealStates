package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {


}
