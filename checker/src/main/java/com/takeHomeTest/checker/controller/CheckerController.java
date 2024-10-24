package com.takeHomeTest.checker.controller;

import com.takeHomeTest.checker.constant.APIs;
import com.takeHomeTest.checker.service.intrface.CheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIs.PATH_CHECK)
public class CheckerController {

    private final CheckerService checkerService;

    @PostMapping
    public ResponseEntity<String> checkerUser(@RequestParam String nameSearch) {
        String response = checkerService.checkUser(nameSearch);

        return ResponseEntity.status(201).body(response);
    }

}
