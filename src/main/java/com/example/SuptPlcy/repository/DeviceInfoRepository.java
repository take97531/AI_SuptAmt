package com.example.SuptPlcy.repository;

import com.example.SuptPlcy.entity.DeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceInfoRepository extends JpaRepository<DeviceInfoEntity, String> {
}