package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyCountDTO;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyFetchDTO;
import org.hibernate.annotations.BatchSize;
import com.academicproject.moomin.realstates.entity.PropertyTypes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {

    List<Property> findAll(Specification<Property> specification);
    @Query("SELECT p FROM Property p LEFT JOIN FETCH p.location WHERE p.featured = true")
    List<Property> getFeaturedProperty();

    @Query("SELECT p FROM Property p join fetch p.location")
    List<Property> getTest();


    @Query("SELECT p FROM Property p ORDER BY p.id DESC")
    List<Property> findLast8AddedProperties(Pageable pageable);

    @Query("SELECT p FROM Property p WHERE p.propertyTypes = :category")
    List<Property> findByCategoryWithLocation(@Param("category") PropertyTypes category);

    @Query("SELECT COUNT(p) FROM Property p WHERE p.propertyTypes = :category")
    Integer countByCategory(@Param("category") PropertyTypes category);

    @Query("SELECT p.propertyTypes AS propertyType, COUNT(p) AS count FROM Property p GROUP BY p.propertyTypes")
    List<Object> countPropertyTypes();

}
