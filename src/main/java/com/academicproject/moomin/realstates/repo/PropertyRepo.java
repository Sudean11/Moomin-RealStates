package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Property;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {

    List<Property> findAll(Specification<Property> specification);

}
