package controller;

import dto.SubscriptionDtoOutput;
import dto.UserDtoOutput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import dto.UserDtoInput;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDtoOutput userCreate(@Valid @RequestBody final UserDtoInput userDtoInput) {
        return userService.userCreate(userDtoInput);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoOutput userUpdate(@PathVariable("id") @Positive final Long userId, @Valid @RequestBody final UserDtoInput userDtoInput) {
        return userService.userUpdate(userId, userDtoInput);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoOutput getUserById(@PathVariable("id") @Positive final Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@PathVariable("id") @Positive final Long userId) {
        userService.userDelete(userId);
    }

    @PostMapping("/{id}/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionDtoOutput addSubscription(@PathVariable("id") @Positive final Long userId, @Positive final Long subId) {
        return userService.addSubscription(userId, subId);
    }

    @GetMapping("/{id}/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionDtoOutput> getAllSubscriptions(@PathVariable("id") @Positive final Long userId, @RequestParam(defaultValue = "0") @PositiveOrZero final int from,
                                                   @RequestParam(defaultValue = "10") @Positive final int size) {
        return userService.getAllSubscriptions(userId, from, size);
    }

    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable("id") @Positive final Long userId, @PathVariable("sub_id") @Positive final Long subId) {
        userService.deleteSubscription(userId, subId);
    }
}
