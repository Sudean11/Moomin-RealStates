package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.entity.dtos.requestDto.UserDto;
import com.academicproject.moomin.realstates.repo.UserRepo;
import com.academicproject.moomin.realstates.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(@RequestParam boolean unverified){
        return userService.findAll(unverified);
    }

    @Autowired
    UserRepo userRepo;

    @PostMapping
    public void saveUser(@RequestBody UserDto user){
        userService.saveUser(user);

    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
    }

    @PostMapping("/{userId}/verify")
    public ResponseEntity<User> verifyUser(@PathVariable("userId") int userId) {
        userService.verifyUser(userId);
        return ResponseEntity.ok().build();
    }

}