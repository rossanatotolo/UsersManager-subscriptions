package controller;

import dto.SubscriptionDtoInput;
import dto.SubscriptionDtoOutput;
import dto.UserDtoInput;
import dto.UserDtoOutput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import service.SubscriptionService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionDtoOutput subscriptionCreate(@Valid @RequestBody final SubscriptionDtoInput subscriptionDtoInput) {
        return subscriptionService.subscriptionCreate(subscriptionDtoInput);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void subscriptionDelete(@PathVariable("id") @Positive final Long subId) {
        subscriptionService.subscriptionDelete(subId);
    }

}
