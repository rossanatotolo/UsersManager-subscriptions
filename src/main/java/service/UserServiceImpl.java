package service;

import dto.SubscriptionDtoInput;
import dto.SubscriptionDtoOutput;
import dto.UserDtoInput;
import dto.UserDtoOutput;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.SubscriptionMapper;
import mapper.UserMapper;
import model.Subscription;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.SubscriptionRepository;
import repository.UserRepository;

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

    public SubscriptionDtoOutput addSubscription(final Long userId, final SubscriptionDtoInput subscriptionDtoInput) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = {} не существует." + userId));

        final Subscription subscription = subscriptionRepository.save(SubscriptionMapper.toSubscription(subscriptionDtoInput));
        log.info("Комментарий к событию с id = {} добавлен.", eventId);
        return CommentMapper.toCommentOutputDto(comment);
    }

    public List<SubscriptionDtoOutput> getAllSubscriptions(final Long userId, final int from, final int size) {

    }

    public void deleteSubscription(final Long userId, final Long subId) {

    }
}
