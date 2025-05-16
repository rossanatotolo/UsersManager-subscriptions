package service;

import dto.SubscriptionDtoOutput;
import dto.UserDtoInput;
import dto.UserDtoOutput;

import java.util.List;

public interface UserService {
    UserDtoOutput userCreate(final UserDtoInput userDtoInput);

    UserDtoOutput userUpdate(final Long userId, final UserDtoInput userDtoInput);

    UserDtoOutput getUserById(final Long userId);

    void userDelete(final Long userId);

    SubscriptionDtoOutput addSubscription(final Long userId, final Long subId);

    List<SubscriptionDtoOutput> getAllSubscriptions(final Long userId, final int from, final int size);

    void deleteSubscription(final Long userId, final Long subId);
}
