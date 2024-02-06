package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public void deleteById(Long id) {

    }

    @Override
    public User findById(int id) {
        return null;
    }


    @Override
    public void saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
    public void verifyUser(int userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus("verified");
            userRepo.save(user);
        } else {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }
    }
}
