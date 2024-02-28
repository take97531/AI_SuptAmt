package com.example.device.repository;

import com.example.device.entity.DeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @class   : 모델 정보 Repository
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
public interface DeviceInfoRepository extends JpaRepository<DeviceInfoEntity, String> {
}
