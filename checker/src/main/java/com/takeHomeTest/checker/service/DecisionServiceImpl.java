package com.takeHomeTest.checker.service;

import com.takeHomeTest.checker.constant.Decision;
import com.takeHomeTest.checker.service.intrface.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecisionServiceImpl implements DecisionService {
    @Override
    public String decision(Double threshold) {
        String result = Decision.AUTO_NOT_MATCH;

        if (threshold >= 80.0) {
            result = Decision.AUTO_MATCH;
        } else if (threshold >= 60.0 ) {
            result = Decision.AMBIGUOUS;
        } else {
            return result;
        }

        return result;
    }
}
