package com.takeHomeTest.checker.controller;

import com.takeHomeTest.checker.constant.APIs;
import com.takeHomeTest.checker.service.intrface.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIs.PATH_DECISION)
public class DecisionController {

    private final DecisionService decisionService;

    @PostMapping
    public ResponseEntity<String> checkDecision(@RequestParam Double threshold) {
        String response = decisionService.decision(threshold);

        return ResponseEntity.status(201).body(response);

    }
}
