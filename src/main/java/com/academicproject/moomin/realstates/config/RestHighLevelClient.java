//package com.academicproject.moomin.realstates.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;
//
//@Configuration
//public class RestHighLevelClient extends ReactiveElasticsearchConfiguration {
//
//    @Override
//    public ClientConfiguration clientConfiguration() {
//        return ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .build();
//    }
//}