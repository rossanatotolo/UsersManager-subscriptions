package com.example.service;

import com.example.model.Subscription;
import com.example.model.User;
import com.example.dto.SubscriptionDtoOutput;
import com.example.dto.UserDtoInput;
import com.example.dto.UserDtoOutput;
import com.example.exception.DuplicatedDataException;
import com.example.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.mapper.SubscriptionMapper;
import com.example.mapper.UserMapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.repository.SubscriptionRepository;
import com.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public UserDtoOutput userCreate(final UserDtoInput userDtoInput) {
        final User user = userRepository.save(UserMapper.toUser(userDtoInput));
        log.info("Пользователь с id = {} добавлен.", user.getId());
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDtoOutput userUpdate(final Long userId, final UserDtoInput userDtoInput) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = {} не существует." + userId));

        if (Objects.nonNull(userDtoInput.getName())) {
            user.setName(userDtoInput.getName());
        }
        if (Objects.nonNull(userDtoInput.getEmail())) {
            user.setEmail(userDtoInput.getEmail());
        }

        final User userUpdate = userRepository.save(user);
        log.info("Пользователь с id = {} обновлен.", user.getId());
        return UserMapper.toUserDto(userUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDtoOutput getUserById(final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = {} не найден." + userId));

        log.info("Получение пользователя по id = {}.", userId);
        return UserMapper.toUserDto(user);
    }

    @Override
    public void userDelete(final Long userId) {
        userRepository.deleteById(userId);
        log.info("Пользователь с id  = {} удален.", userId);
    }

    @Override
    public SubscriptionDtoOutput addSubscription(final Long userId, final Long subId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = {} не существует." + userId));

        final Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NotFoundException("Подписки с id = {} не существует." + subId));

        if (user.getSubscriptions().contains(subscription)) {
            throw new DuplicatedDataException("Повторное добавление подписки невозможно.");
        }

        subscription.getUsers().add(user);
        user.getSubscriptions().add(subscription);

        log.info("Пользователь с id = {} добавил подписку с id = {}.", userId, subId);
        return SubscriptionMapper.toSubscriptionDto(subscription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionDtoOutput> getAllSubscriptions(final Long userId, final int from, final int size) {
        PageRequest pageRequest = PageRequest.of(from / size, size);

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = {} не существует." + userId));

        final List<Subscription> subscriptions = subscriptionRepository.findByUsers(user, pageRequest);

        if (subscriptions.isEmpty()) {
            log.info("Спискок подписок у пользователя с id = {} не найден.", userId);
            return new ArrayList<>();
        }

        log.info("Получение списка подписок пользователя с id = {}.", userId);
        return subscriptions.stream()
                .map(SubscriptionMapper::toSubscriptionDto)
                .toList();
    }

    @Override
    public void deleteSubscription(final Long userId, final Long subId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = {} не существует." + userId));

        final Subscription subscription = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NotFoundException("Подписки с id = {} не существует." + subId));

        subscription.getUsers().remove(user);
        user.getSubscriptions().remove(subscription);

        subscriptionRepository.save(subscription);
        userRepository.save(user);
        log.info("Пользователь с id = {} удалил подписку с id = {}.", userId, subId);
    }
}
