package service;

import dto.SubscriptionDtoInput;
import dto.SubscriptionDtoOutput;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.SubscriptionMapper;
import model.Subscription;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.SubscriptionRepository;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionDtoOutput subscriptionCreate(final SubscriptionDtoInput subscriptionDtoInput) {
        final Subscription subscription = subscriptionRepository.save(SubscriptionMapper.toSubscription(subscriptionDtoInput));
        log.info("Подписка с id = {} добавлена.", subscription.getId());
        return SubscriptionMapper.toSubscriptionDto(subscription);
    }

    @Override
    public void subscriptionDelete(final Long subId) {
        subscriptionRepository.deleteById(subId);
        log.info("Подписка с id  = {} удалена.", subId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionDtoOutput> getTopSubscriptions(final int count) {
        Pageable pageRequest = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "users.size"));
        List<Subscription> topSubscriptions = subscriptionRepository.findAll(pageRequest).getContent();

        if (topSubscriptions.isEmpty()) {
            log.warn("Подписки для запрошенного топа {} не найдены.", count);
            throw new NotFoundException("Не найдено топ-3 подписок по популярности.");
        }

        return topSubscriptions.stream()
                .map(SubscriptionMapper::toSubscriptionDto)
                .toList();
    }
}
