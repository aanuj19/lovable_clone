package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.aanuj.lovable_clone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsage(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
