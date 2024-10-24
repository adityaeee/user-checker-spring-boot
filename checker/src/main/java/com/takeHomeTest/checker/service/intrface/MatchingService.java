package com.takeHomeTest.checker.service.intrface;

import com.takeHomeTest.checker.dto.UserMatching;

public interface MatchingService {
    Double calculateSimilarity(UserMatching userMatching);
}
