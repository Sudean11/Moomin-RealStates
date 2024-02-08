package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Message;
import com.academicproject.moomin.realstates.entity.Offer;
import com.academicproject.moomin.realstates.repo.MessageRepo;
import com.academicproject.moomin.realstates.repo.OfferRepo;
import com.academicproject.moomin.realstates.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;
    private final OfferRepo offerRepo;

    @Autowired
    public MessageServiceImpl(MessageRepo messageRepo, OfferRepo offerRepo) {
        this.messageRepo = messageRepo;
        this.offerRepo = offerRepo;
    }

    @Override
    @Transactional
    public void save(Message message, Long offerId) {
        Optional<Offer> optionalOffer = offerRepo.findById(offerId);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            message.setOffer(offer); // Set the Offer object for the Message
            messageRepo.save(message); // Save the Message entity
        } else {
            throw new IllegalArgumentException("Offer not found for ID: " + offerId);
        }
    }

    @Override
    public List<Message> findAllByEmail(String email) {
       return  messageRepo.findAllByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        messageRepo.deleteById(id);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepo.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll();
    }
}
