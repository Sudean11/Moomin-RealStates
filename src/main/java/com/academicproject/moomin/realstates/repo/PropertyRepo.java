package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Property;
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
    @Query("SELECT p FROM Property p WHERE p.type LIKE %:type%")
    List<Property> findByTypeContaining(@Param("type") String type);

    // Query for finding properties in matching state
    @Query("SELECT p FROM Property p WHERE p.location.state LIKE %:state%")
    List<Property> findByLocation_StateContaining(@Param("state") String state);

    // Query for finding properties in matching city
    @Query("SELECT p FROM Property p WHERE p.location.city LIKE %:city%")
    List<Property> findByLocation_CityContaining(@Param("city") String city);

    // Query for finding properties with matching zip code
    @Query("SELECT p FROM Property p WHERE p.location.zipCode LIKE %:zip%")
    List<Property> findByLocation_ZipCodeContaining(@Param("zip") String zip);

    @Query("SELECT p FROM Property p WHERE p.area LIKE %:area%")
    List<Property> findByAreaContaining(@Param("area") String area);

    // Additional queries for combinations
    List<Property> findByLocation_CityContainingAndLocation_ZipCodeContainingAndLocation_StateContaining(
            @Param("city") String city, @Param("zip") String zip, @Param("state") String state);

    List<Property> findByLocation_CityContainingAndLocation_ZipCodeContaining(
            @Param("city") String city, @Param("zip") String zip);

    List<Property> findByLocation_CityContainingAndLocation_StateContaining(
            @Param("city") String city, @Param("state") String state);

    List<Property> findByLocation_CityContainingAndAreaContaining(
            @Param("city") String city, @Param("area") String area);

    List<Property> findByAreaContainingAndLocation_ZipCodeContaining(
            @Param("area") String area, @Param("zip") String zip);

    List<Property> findByTypeContainingAndLocation_ZipCodeContaining(
            @Param("type") String type, @Param("zip") String zip);

    List<Property> findByTypeContainingAndLocation_StateContaining(
            @Param("type") String type, @Param("state") String state);

    List<Property> findByAreaContainingAndLocation_StateContaining(
            @Param("area") String area, @Param("state") String state);


}
