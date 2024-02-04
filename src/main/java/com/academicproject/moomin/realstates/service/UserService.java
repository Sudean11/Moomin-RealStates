package com.academicproject.moomin.realstates.service;


import com.academicproject.moomin.realstates.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {


    List<User> findAll();

    User findById(int id);



    void deleteById(int id);
}
