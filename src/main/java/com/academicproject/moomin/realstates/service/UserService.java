package com.academicproject.moomin.realstates.service;


import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.UserDto;

import java.util.List;

public interface UserService {


    List<User> findAll(Boolean unverified);

    void deleteById(Long id);

    User findById(int id);

    void saveUser(UserDto user);

    User findById(Long id);

    void deleteById(Integer id);

    void verifyUser(int userId);
}
