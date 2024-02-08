package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query("select m from Message m join fetch m.offer o join fetch o.property p join fetch p.user u where u.email=:email")
    List<Message> findAllByEmail(String email);
}
