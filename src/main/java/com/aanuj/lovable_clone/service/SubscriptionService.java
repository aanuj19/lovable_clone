package com.aanuj.lovable_clone.service;

import com.aanuj.lovable_clone.dto.subscription.CheckoutRequest;
import com.aanuj.lovable_clone.dto.subscription.CheckoutResponse;
import com.aanuj.lovable_clone.dto.subscription.PortalResponse;
import com.aanuj.lovable_clone.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse getCurrentSubscription();
}
