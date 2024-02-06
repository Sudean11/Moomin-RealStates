package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.Message;
import com.academicproject.moomin.realstates.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/offer/{offerId}/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@PathVariable("offerId") Long offerId, @RequestBody Message message) {
        messageService.save(message,offerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        return messageOptional.map(message -> ResponseEntity.ok().body(message))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessagesByOfferId(@PathVariable("offerId") Long offerId) {
        // Here you can use offerId to fetch messages specific to the offer
        List<Message> messages = messageService.findAllByOfferId(offerId);
        return ResponseEntity.ok().body(messages);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable("id") Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
