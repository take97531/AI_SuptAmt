package com.example.suptplcy.repository;

import com.example.suptplcy.entity.DeviceSubsidyPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceSubsidyPolicyRepository extends JpaRepository<DeviceSubsidyPolicyEntity, String> {
    public List<DeviceSubsidyPolicyEntity> findByDeviceCodeAndPlanCode(String deviceCode, String planCode);
}
