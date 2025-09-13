package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepositorty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepositorty recommendationRepositorty;


    public List<Recommendation> getUserRecommendation(String userId) {
        return  recommendationRepositorty.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepositorty.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("Recommendation not found for activityId: " + activityId));
    }
}
