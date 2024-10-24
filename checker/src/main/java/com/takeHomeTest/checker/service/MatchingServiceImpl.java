package com.takeHomeTest.checker.service;

import com.takeHomeTest.checker.dto.UserMatching;
import com.takeHomeTest.checker.service.intrface.MatchingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.similarity.JaccardSimilarity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final JaccardSimilarity jaccardSimilarity;

    @Override
    public Double calculateSimilarity(UserMatching userMatching) {
        Double similarity = jaccardSimilarity.apply(userMatching.getName(), userMatching.getDbName());

        return similarity * 100;
    }
}
