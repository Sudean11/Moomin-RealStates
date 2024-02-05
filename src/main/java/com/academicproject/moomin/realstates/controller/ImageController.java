package com.academicproject.moomin.realstates.controller;

import ch.qos.logback.core.model.Model;
import com.academicproject.moomin.realstates.entity.Image;
import com.academicproject.moomin.realstates.repo.ImageRepo;
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

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
//        model.addAttribute("image", new Image());
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@ModelAttribute Image image, @RequestParam("file") MultipartFile file) {
        try {
            image.setName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setData(file.getBytes());
            imageRepository.save(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/upload";
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

