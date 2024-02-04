package com.academicproject.moomin.realstates.Controller;

import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
public class PropertyController {

    PropertyService propertyService;

    @Autowired
    PropertyController(PropertyService propertyService){
        this.propertyService = propertyService;
    }


    @GetMapping
    public List<Property> getProperty(){
        return propertyService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id){
        return propertyService.findById(id);
    }
    @PostMapping("/{id}")
    public void saveProperty(@RequestBody Property property){
        propertyService.save(property);
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
