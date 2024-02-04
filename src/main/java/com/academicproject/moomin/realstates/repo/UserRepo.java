package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    // Add custom query methods if needed
}
