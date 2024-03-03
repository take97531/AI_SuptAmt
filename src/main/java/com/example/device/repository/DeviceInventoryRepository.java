package com.example.device.repository;

import com.example.device.entity.DeviceInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @class   : 모델 재고 Repository
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
public interface DeviceInventoryRepository extends JpaRepository<DeviceInventoryEntity, String> {
    Optional<DeviceInventoryEntity> findByDeviceCodeAndDeviceNumber(String deviceCode, String deviceNumber);

    @Transactional
    @Modifying
    @Query("UPDATE DeviceInventoryEntity d " +
            "SET d.deviceUsage = :deviceUsage " +
            "WHERE d.deviceCode = :deviceCode AND d.deviceNumber = :deviceNumber")
    void updateDeviceUsage(@Param("deviceCode") String deviceCode,
                           @Param("deviceNumber") String deviceNumber,
                           @Param("deviceUsage") String deviceUsage);

}
