package service;

import dto.SubscriptionDtoInput;
import dto.SubscriptionDtoOutput;
import dto.UserDtoInput;
import dto.UserDtoOutput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface UserService {
    UserDtoOutput userCreate(final UserDtoInput userDtoInput);

    UserDtoOutput userUpdate(final Long userId, final UserDtoInput userDtoInput);

    UserDtoOutput getUserById(final Long userId);

    void userDelete(final Long userId);

    SubscriptionDtoOutput addSubscription(final Long userId, final SubscriptionDtoInput subscriptionDtoInput);

    List<SubscriptionDtoOutput> getAllSubscriptions(final Long userId, final int from, final int size);

    void deleteSubscription(final Long userId, final Long subId);
}
