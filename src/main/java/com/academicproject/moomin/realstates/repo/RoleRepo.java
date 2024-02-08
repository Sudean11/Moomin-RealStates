package com.academicproject.moomin.realstates.repo;

import com.academicproject.moomin.realstates.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer > {
}
