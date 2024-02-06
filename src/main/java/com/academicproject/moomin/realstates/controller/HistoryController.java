package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.History;
import com.academicproject.moomin.realstates.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/history")
public class HistoryController {

    HistoryService historyService;

public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public List<History> getHistory() {
        return historyService.getHistory();
    }


@PostMapping
    public String saveHistory(@RequestBody History history) {
        return historyService.saveHistory(history);
    }

    @GetMapping("/{id}")
    public Optional<History> getHistoryById(Long id) {
        return historyService.getHistoryById(id);
    }




}
