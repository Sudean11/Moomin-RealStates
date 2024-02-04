package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {



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
    public void deleteById(int id) {
        userRepo.deleteById(id);
    }
    @Override
    public User findById(int id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null); // or handle it differently based on your requirements
    }
}
