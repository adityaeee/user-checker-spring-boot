package com.takeHomeTest.checker.service;

import com.takeHomeTest.checker.constant.Checker;
import com.takeHomeTest.checker.constant.Decision;
import com.takeHomeTest.checker.dto.UserMatching;
import com.takeHomeTest.checker.entity.User;
import com.takeHomeTest.checker.service.intrface.CheckerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckerServiceImpl implements CheckerService {

    private final RestClient restClient;

    @Override
    public String checkUser(String nameSearch) {

        boolean foundAmbiguous = false;
        Double ambiguousSimilarityScore = 0.0;

        String userApiUrl = "http://localhost:8080/api/users/search";
        URI userUri = UriComponentsBuilder.fromUriString(userApiUrl)
                .queryParam("name", nameSearch)
                .build()
                .toUri();

        ResponseEntity<List<User>> userResponse = restClient.get()
                .uri(userUri)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<User>>() {});

        List<User> users = userResponse.getBody();

        if (users == null || users.isEmpty()) {
            return Checker.USER_NOT_DETECTED;
        }

        for (User user : users) {
            String matchingApiUrl = "http://localhost:8080/api/match";
            URI matchingUri = UriComponentsBuilder.fromUriString(matchingApiUrl)
                    .build()
                    .toUri();

            UserMatching userMatching = UserMatching.builder()
                    .name(nameSearch)
                    .dbName(user.getName())
                    .build();

            ResponseEntity<Double> matchResponse = restClient.post()
                    .uri(matchingUri)
                    .body(userMatching)
                    .retrieve()
                    .toEntity(Double.class);

            Double similarityScore = matchResponse.getBody();
            System.out.println("==========================================================");
            System.out.println(similarityScore);
            System.out.println("==========================================================");

            String decisionApiUrl = "http://localhost:8080/api/decision";
            URI decisionUri = UriComponentsBuilder.fromUriString(decisionApiUrl)
                    .queryParam("threshold", similarityScore)
                    .build()
                    .toUri();

            ResponseEntity<String> decisionResponse = restClient.post()
                    .uri(decisionUri)
                    .retrieve()
                    .toEntity(String.class);

            String decision = decisionResponse.getBody();
            System.out.println("==========================================================");
            System.out.println(decision);
            System.out.println("==========================================================");

            if (Decision.AUTO_MATCH.equals(decision)) {
                return Checker.USER_DETECTED + " : " + user.getName() + ", Similarity Score: " + similarityScore;
            }

            if (Decision.AMBIGUOUS.equals(decision)) {
                foundAmbiguous = true;
                ambiguousSimilarityScore = similarityScore;
            }
        }

        if (foundAmbiguous) {
            return Checker.CANNOT_DETERMINE + ", Similarity Score: " + ambiguousSimilarityScore;
        }

        return Checker.USER_NOT_DETECTED;
    }
}
