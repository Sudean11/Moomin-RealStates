package com.academicproject.moomin.realstates.service;


import com.academicproject.moomin.realstates.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {


    List<User> findAll();

    void deleteById(Long id);

    User findById(int id);

    void saveUser(User user);




    User findById(Long id);

    void deleteById(Integer id);
}
