package com.academicproject.moomin.realstates.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.academicproject.moomin.realstates.entity.ElasticProduct;
import com.academicproject.moomin.realstates.entity.Property;
import com.academicproject.moomin.realstates.entity.PropertyTypes;
import com.academicproject.moomin.realstates.entity.dtos.kafkaDto.EmailObject;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.PropertyRequestDto;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyCountDTO;
import com.academicproject.moomin.realstates.entity.dtos.responseDto.PropertyFetchDTO;
import com.academicproject.moomin.realstates.helper.ListMapper;
import com.academicproject.moomin.realstates.helper.MessageProducer;
import com.academicproject.moomin.realstates.repo.ElasticRepo;
import com.academicproject.moomin.realstates.repo.PropertyRepo;
import com.academicproject.moomin.realstates.service.PropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.lucene.index.Term;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.SearchTemplateQuery;
import org.springframework.data.elasticsearch.core.query.SearchTemplateQueryBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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



    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    ElasticRepo elasticRepo;



    @GetMapping("/kafka")
    public void kafkaProducer() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String emailObjectJsonString = mapper.writeValueAsString(new EmailObject("task1", "create transaction", "create transaction"));
        messageProducer.sendMessage("topic", emailObjectJsonString);
    }


    @Autowired
    ElasticsearchClient elasticsearchClient;

    @GetMapping("/elastic/{searchText}")
    public List<ElasticProduct> elasticSearch(@PathVariable String searchText) throws IOException {
        Supplier<Query> supplier =()->Query.of(q->q.fuzzy(createFuzzyQuery(searchText)));
//        SearchResponse response = elasticsearchClient.search(s -> s
//                .index("product")
//                .query(createFuzzyQuery(searchText)) // Directly pass the created SearchQuery
//        );


        return  null;
    }



    public static co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery createFuzzyQuery(String approx){
        val fuzzyQuery =  new FuzzyQuery.Builder();
        return fuzzyQuery.field("name").value(approx).build();
    }

    @GetMapping("/user")
    public List<Property> getPropertyByEmail(
            @RequestParam String email
    ){
        return propertyService.getPropertyByEmail(email);
    }


    @GetMapping
    public List<Property> getProperty(
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "") String area,
            @RequestParam(required = false, defaultValue = "") String zip,
            @RequestParam(required = false, defaultValue = "") String state,
            @RequestParam(required = false, defaultValue = "") String city,
            @RequestParam(required = false, defaultValue = "") String bathrooms,
            @RequestParam(required = false, defaultValue = "") String beds,
            @RequestParam(required = false, defaultValue = "") String priceRange
    ){
        return propertyService.findAll(category,area,zip,state,city, bathrooms, beds, priceRange);
    }

    @GetMapping("/{id}")
    public Optional<Property> getPropertyById(@PathVariable Long id){
        return propertyService.findById(id);
    }


    @PostMapping("/{id}/cancel-contingency")
    public void cancelContingency(@PathVariable Long id){
        Property property = propertyRepo.findById(id).get();
        property.setStatus("AVAILABLE");
        propertyRepo.save(property);
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

    @PostMapping("/{id}/delete")
    public void deletePropertyById(@PathVariable long id){
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
