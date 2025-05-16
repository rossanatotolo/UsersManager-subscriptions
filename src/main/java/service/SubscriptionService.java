package service;

import dto.SubscriptionDtoInput;
import dto.SubscriptionDtoOutput;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDtoOutput subscriptionCreate(final SubscriptionDtoInput subscriptionDtoInput);

    void subscriptionDelete(final Long subId);

    List<SubscriptionDtoOutput> getTopSubscriptions(final int count);
}
