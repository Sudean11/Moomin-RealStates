package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    void save(Message message,Long offerId);
    void deleteById(Long id);
    Optional<Message> findById(Long id);
    List<Message> findAll();
    List<Message> findAllByEmail(String email);



}
