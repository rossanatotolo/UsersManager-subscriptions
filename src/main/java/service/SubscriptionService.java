package service;

import dto.SubscriptionDtoInput;
import dto.SubscriptionDtoOutput;

public interface SubscriptionService {
    SubscriptionDtoOutput subscriptionCreate(final SubscriptionDtoInput subscriptionDtoInput);

    void subscriptionDelete(final Long subId);

}
