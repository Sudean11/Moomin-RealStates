package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.ElasticProduct;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ElasticRepo extends ElasticsearchRepository<ElasticProduct, Integer> {

}
