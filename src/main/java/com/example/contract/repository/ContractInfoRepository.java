package com.example.contract.repository;

import com.example.contract.entity.ContractInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractInfoRepository extends JpaRepository<ContractInfoEntity, String> {
    Optional<ContractInfoEntity> findBySubscriptionId(String subscriptionId);
    // 추가적인 메서드가 필요한 경우 여기에 선언합니다.
}
