package com.takeHomeTest.checker.controller;

import com.takeHomeTest.checker.constant.APIs;
import com.takeHomeTest.checker.dto.UserRequest;
import com.takeHomeTest.checker.entity.User;
import com.takeHomeTest.checker.service.intrface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIs.PATH_USER)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> searchUser() {
        return ResponseEntity.status(200).body("BERhasil");
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam String name) {
        List<User> users = userService.getAllUsers(name);

        return ResponseEntity.status(200).body(users);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserRequest request) {
        User user = userService.createUser(request);

        return ResponseEntity.status(201).body(user);
    }
    

}
