package com.example.device.repository;

import com.example.device.entity.DeviceInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @class   : 모델 재고 Repository
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
public interface DeviceInventoryRepository extends JpaRepository<DeviceInventoryEntity, String> {
    Optional<DeviceInventoryEntity> findByDeviceCodeAndDeviceNumber(String deviceCode, String deviceNumber);
}
