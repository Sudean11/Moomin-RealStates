package com.academicproject.moomin.realstates.controller;

import com.academicproject.moomin.realstates.entity.User;
import com.academicproject.moomin.realstates.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;



    @GetMapping
    public List<User> getUsers(){
        return userService.findAll();
    }



    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
    }




}