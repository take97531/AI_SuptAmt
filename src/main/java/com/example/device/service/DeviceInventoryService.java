package com.example.device.service;


import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.repository.DeviceInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @class   : 모델 재고 Service
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
@Service
public class DeviceInventoryService {

    /** Repository*/
    private final DeviceInventoryRepository repository;

    @Autowired
    public DeviceInventoryService(DeviceInventoryRepository repository) {
        this.repository = repository;
    }

    /**
     * @name    : 모델 재고 전체 조회 함수
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     * */
    public List<DeviceInventoryEntity> findAllDevices() {
        return repository.findAll();
    }
}