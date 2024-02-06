package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
