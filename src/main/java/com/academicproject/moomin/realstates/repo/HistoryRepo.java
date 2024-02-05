package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HistoryRepo extends JpaRepository<History, Long> {
}
