package com.takeHomeTest.checker.controller;


import com.takeHomeTest.checker.constant.APIs;
import com.takeHomeTest.checker.dto.UserMatching;
import com.takeHomeTest.checker.service.intrface.MatchingService;
import com.takeHomeTest.checker.service.intrface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIs.PATH_MATCHING)
public class MatchingController {

    private final MatchingService matchingService;

    @PostMapping
    public ResponseEntity<Double> matchingUser (@RequestBody UserMatching request) {

        UserMatching matching = UserMatching.builder()
                .name(request.getName())
                .dbName(request.getDbName())
                .build();

        Double similarity = matchingService.calculateSimilarity(matching);

        return ResponseEntity.status(201).body(similarity);
    }

}
