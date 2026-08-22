package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.subscription.CheckoutRequest;
import com.aanuj.lovable_clone.dto.subscription.CheckoutResponse;
import com.aanuj.lovable_clone.dto.subscription.PortalResponse;
import com.aanuj.lovable_clone.dto.subscription.SubscriptionResponse;
import com.aanuj.lovable_clone.enums.SubscriptionStatus;

import java.time.Instant;

public interface SubscriptionService {

    SubscriptionResponse getCurrentSubscription();

    void activeSubscription(Long userId, Long planId, String subscriptionId, String customerId);

    void updateSubscription(String subscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);

    void cancelSubsription(String subscriptionId);

    void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd);

    void markSubscriptionPastDue(String subId);
}
