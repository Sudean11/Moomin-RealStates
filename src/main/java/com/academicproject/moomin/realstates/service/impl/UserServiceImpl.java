package com.academicproject.moomin.realstates.service.impl;

import com.academicproject.moomin.realstates.entity.Location;
import com.academicproject.moomin.realstates.entity.Role;
import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.UserDto;
import com.academicproject.moomin.realstates.repo.LocationRepo;
import com.academicproject.moomin.realstates.repo.RoleRepo;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<User> findAll(Boolean unverified) {
        if(unverified){
            return userRepo.findAll().stream()
                    .filter(x->x.getStatus().equals("unverified"))
                    .filter(x->x.getRole().getRole().equals("OWNER"))
                    .collect(Collectors.toList());
        }
        return userRepo.findAll();
    }





    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findById(int id) {
        return null;
    }


    @Autowired
    RoleRepo roleRepo;

    @Autowired
    LocationRepo locationRepo;

    @Override
    public void saveUser(UserDto user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User newUser = new User();

        newUser.setFirstname(user.getFirstName());
        newUser.setLastname(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setContact(user.getPhoneNumber());


        Location location = locationRepo.findById(6L).get();
        newUser.setAddress(location);
        Role role;
        if(user.getUserType().equals("buyer")){
            role = roleRepo.findById(3).get();
        }else{
            role = roleRepo.findById(1).get();
        }
        newUser.setAddress(location);
        newUser.setRole(role);
        userRepo.save(newUser);
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
