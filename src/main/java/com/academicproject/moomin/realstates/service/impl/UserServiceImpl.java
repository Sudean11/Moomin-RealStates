package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @PersistenceContext
    EntityManager entityManager;

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById( Long id) {
        return userRepo.findById(id).orElse(null);
    }


    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);

    }







}
