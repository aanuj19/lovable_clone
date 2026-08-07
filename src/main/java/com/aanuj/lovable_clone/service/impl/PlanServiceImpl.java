package com.aanuj.lovable_clone.service.impl;

import com.aanuj.lovable_clone.dto.subscription.PlanResponse;
import com.aanuj.lovable_clone.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllPlans() {
        return List.of();
    }
}
