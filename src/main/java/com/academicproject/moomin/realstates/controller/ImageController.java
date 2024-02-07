package com.academicproject.moomin.realstates.controller;

import ch.qos.logback.core.model.Model;
import com.academicproject.moomin.realstates.entity.Image;
import com.academicproject.moomin.realstates.repo.ImageRepo;
import com.academicproject.moomin.realstates.service.impl.ImgurService;
import com.academicproject.moomin.realstates.service.impl.ImgurUploader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller


public class ImageController {

    @Autowired
    private ImageRepo imageRepository;

    public ImageController(ImgurService imgurService) {
        this.imgurService = imgurService;
    }

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
//        model.addAttribute("image", new Image());
        return "uploadForm";
    }

    @Autowired
    private ImgurUploader imgurUploader;

    private final ImgurService imgurService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
//            return new ResponseEntity<>("Image is required", HttpStatus.BAD_REQUEST);
        return null;
        }

        try {
            byte[] imageData = image.getBytes();
            String imgurResponse = imgurService.uploadImage(imageData);
            return ResponseEntity.ok(extractImageLink(imgurResponse));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    ObjectMapper objectMapper = new ObjectMapper();

    public String extractImageLink(String responseBody) throws IOException {
        JsonNode rootNode =  objectMapper.readTree(responseBody);
        JsonNode dataNode = rootNode.get("data");
        if (dataNode != null && dataNode.has("link")) {
            return dataNode.get("link").asText();
        } else {
            throw new IOException("Failed to extract image link from Imgur response");
        }
    }
    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getContentType()))
                    .body(image.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

