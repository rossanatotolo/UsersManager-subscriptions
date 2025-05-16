package com.example.mapper;

import com.example.dto.SubscriptionDtoInput;
import com.example.dto.SubscriptionDtoOutput;
import com.example.model.Subscription;

import java.util.HashSet;

public class SubscriptionMapper {

    public static Subscription toSubscription(final SubscriptionDtoInput subscriptionDtoInput) {
        final Subscription subscription = new Subscription();

        subscription.setName(subscriptionDtoInput.getName());
        subscription.setUsers(new HashSet<>());

        return subscription;
    }

    public static SubscriptionDtoOutput toSubscriptionDto(final Subscription subscription) {
        final SubscriptionDtoOutput subscriptionDtoOutput = new SubscriptionDtoOutput();

        subscriptionDtoOutput.setId(subscription.getId());
        subscriptionDtoOutput.setName(subscription.getName());

        return subscriptionDtoOutput;
    }
}
