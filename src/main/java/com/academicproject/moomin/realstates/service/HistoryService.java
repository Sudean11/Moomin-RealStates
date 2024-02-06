package com.academicproject.moomin.realstates.service;

import com.academicproject.moomin.realstates.entity.History;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HistoryService {
    List<History> getHistory();

    Optional<History> getHistoryById(Long id);

    String saveHistory(History history);


}
