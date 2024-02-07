package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.PropertyTypes;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.PropertyRequestDto;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyCountDTO;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyFetchDTO;
import com.academicproject.moomin.realstates.helper.ListMapper;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
@CrossOrigin(origins = "*")
public class PropertyController {
    PropertyService propertyService;
    @Autowired
    PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    ListMapper listMapper;

    @GetMapping
    public List<Property> getProperty(
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "") String area,
            @RequestParam(required = false, defaultValue = "") String zip,
            @RequestParam(required = false, defaultValue = "") String state,
            @RequestParam(required = false, defaultValue = "") String city,
            @RequestParam(required = false, defaultValue = "") String bathRoom,
            @RequestParam(required = false, defaultValue = "") String bedRoom,
            @RequestParam(required = false, defaultValue = "") String priceRange
    ){
        return propertyService.findAll(category,area,zip,state,city, bathRoom, bedRoom, priceRange);
    }

    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id){
        return propertyService.findById(id);
    }

    @PostMapping
    public void saveProperty( @ModelAttribute PropertyRequestDto propertyRequestDto){
            propertyService.save(propertyRequestDto);
    }

    @PutMapping("/{id}")
    public void updateProperty(@RequestBody Property property){
        propertyService.update(property);
    }

    @DeleteMapping("/{id}")
    public  void deleteProperty(@PathVariable long id){
        propertyService.deleteById(id);
    }

    @GetMapping("/featured")
    public List<Property> getFeaturedProperty(){
        return propertyService.getFeaturedProperty();
    }

    @GetMapping("/recent")
    public List<Property> getRecentProperty(){
        return propertyService.getRecentProperty();
    }
    @GetMapping("/category/{category}")
    public List<Property> getCategoryProperty(@PathVariable PropertyTypes category) {
        return propertyService.findByCategory(category);
    }
    @GetMapping("/category/{category}/count")
    public Integer getRecentProperty(@PathVariable PropertyTypes category) {
        return propertyService.findCount(category);
    }
    @GetMapping("/category/property-count")
    public List<Object> fetchPropertyCountDTO(){
        return propertyService.fetchPropertyCountDTO();
    }

}
