package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.History;
import com.academicproject.moomin.realstates.repo.HistoryRepo;
import com.academicproject.moomin.realstates.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    HistoryRepo historyRepo;

    public HistoryServiceImpl(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }


    @Override
    public List<History> getHistory() {
        return historyRepo.findAll();
    }

    @Override
    public Optional<History> getHistoryById(Long id) {
        return historyRepo.findById(id);
    }

    @Override
    public String saveHistory(History history) {
        historyRepo.save(history);
        return "History saved successfully";
    }




}
