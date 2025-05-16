package com.example.service;

import com.example.dto.SubscriptionDtoInput;
import com.example.dto.SubscriptionDtoOutput;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDtoOutput subscriptionCreate(final SubscriptionDtoInput subscriptionDtoInput);

    void subscriptionDelete(final Long subId);

    List<SubscriptionDtoOutput> getTopSubscriptions(final int count);
}
