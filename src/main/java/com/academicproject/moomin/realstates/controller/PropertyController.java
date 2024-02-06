package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.PropertyRequestDto;
import com.academicproject.moomin.realstates.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    PropertyService propertyService;
    @Autowired
    PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getProperty(
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "") String area,
            @RequestParam(required = false, defaultValue = "") String zip,
            @RequestParam(required = false, defaultValue = "") String state,
            @RequestParam(required = false, defaultValue = "") String city
    ){
        return propertyService.findAll(type,area,zip,state,city);
    }

    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id){
        return propertyService.findById(id);
    }

    @PostMapping
    public void saveProperty(
            @ModelAttribute PropertyRequestDto userl
    ){
//        propertyService.save(property);
        System.out.println("hello");
    }

    @PutMapping("/{id}")
    public void updateProperty(@RequestBody Property property){
        propertyService.update(property);
    }

    @DeleteMapping("/{id}")
    public  void deleteProperty(@PathVariable long id){
        propertyService.deleteById(id);


    }
}
