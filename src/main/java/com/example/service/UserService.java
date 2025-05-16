package com.example.service;

import com.example.dto.SubscriptionDtoOutput;
import com.example.dto.UserDtoInput;
import com.example.dto.UserDtoOutput;

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
