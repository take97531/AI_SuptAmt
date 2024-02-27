package com.example.sales.repository;

import com.example.sales.entity.SubscriptionInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionInfoRepository extends JpaRepository<SubscriptionInfoEntity, String> {
    SubscriptionInfoEntity findBySubscriptionPhone(String subscriptionPhone);
}
