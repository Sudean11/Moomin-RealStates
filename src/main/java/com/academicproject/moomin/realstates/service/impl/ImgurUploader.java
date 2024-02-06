package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.dtos.ImgurResponse;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ImgurUploader {


    RestTemplate restTemplate = new RestTemplate();
    private String clientId = "7d0638aeccf1a23";

    public String uploadImage(byte[] imageData) throws Exception {
        String url = "https://api.imgur.com/3/image";

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("image", imageData);
        map.add("type", "base64");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID " + clientId);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        ResponseEntity<ImgurResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, ImgurResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody().getData().getLink();
        } else {
            throw new Exception(responseEntity.getStatusCode() + ": " + responseEntity.getBody().getData().getError().getMessage());
        }
    }
}