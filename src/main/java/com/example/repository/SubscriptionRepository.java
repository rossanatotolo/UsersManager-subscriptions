package com.example.repository;

import com.example.model.Subscription;
import com.example.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUsers(final User user, final PageRequest pageRequest);
}
