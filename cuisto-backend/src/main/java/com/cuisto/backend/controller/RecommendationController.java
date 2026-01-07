package com.cuisto.backend.controller;

import com.cuisto.backend.service.AIRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final AIRecommendationService aiRecommendationService;

    @GetMapping("/personalized")
    public ResponseEntity<?> getPersonalizedRecommendations(Authentication authentication) {
        // Get user email from authentication
        String userEmail = authentication.getName();

        try {
            var recommendations = aiRecommendationService.getPersonalizedRecommendations(userEmail);
            return ResponseEntity.ok(recommendations);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/daily-challenge")
    public ResponseEntity<?> getDailyChallenge(Authentication authentication) {
        // Get user email from authentication
        String userEmail = authentication.getName();

        try {
            var challenge = aiRecommendationService.getDailyChallenge(userEmail);
            return ResponseEntity.ok(challenge);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}