package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.Message;
import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.MessageDto;
import com.academicproject.moomin.realstates.repo.MessageRepo;
import com.academicproject.moomin.realstates.repo.OfferRepo;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    OfferRepo offerRepo;
    @PostMapping
    public void createMessage( @RequestBody MessageDto message) {
        Message message1 = new Message();
        Offer offer = offerRepo.findById(message.getOfferId()).get();
        message1.setMessage(message.getMessage());
        message1.setEmail(message.getEmail());
        message1.setOffer(offer);
        messageRepo.save(message1);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message -> ResponseEntity.ok().body(message))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessagesByOfferId(@RequestParam String email) {
        // Here you can use offerId to fetch messages specific to the offer
        List<Message> messages = messageService.findAllByEmail(email);
        return ResponseEntity.ok().body(messages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable("id") Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
