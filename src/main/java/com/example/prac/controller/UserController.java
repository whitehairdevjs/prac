package com.example.prac.controller;

import com.example.prac.entity.User;
import com.example.prac.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "전체 유저 정보.", description = "전체 유저 정보 가져오기.")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "id의 유저 정보.", description = "id로 유저 정보 가져오기.")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "유저 정보 저장.", description = "유저 정보 저장하기.")
    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }
}
