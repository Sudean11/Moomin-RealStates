package com.academicproject.moomin.realstates.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Document(indexName = "products")
public class ElasticProduct {

        @Id
        private int id;

        private String name;

        private String description;
}
