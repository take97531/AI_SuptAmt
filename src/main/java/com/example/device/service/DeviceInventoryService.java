package com.example.device.service;


import com.example.device.dto.DeviceInventoryDTO;
import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.repository.DeviceInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    /**
     * @name    : 단말 재고 조회 by 단말코드 , 단말번호
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     * */
    public Optional<DeviceInventoryDTO> findByCodeAndNumber(String deviceCode, String deviceNumber) {
        return repository.findByDeviceCodeAndDeviceNumber(deviceCode, deviceNumber)
                .map(this::convertToDTO);
    }

    private DeviceInventoryDTO convertToDTO(DeviceInventoryEntity deviceInventory) {

        return new DeviceInventoryDTO(
                deviceInventory.getDeviceCode(),
                deviceInventory.getDeviceNumber(),
                deviceInventory.getDeviceUsage(),
                deviceInventory.getCreatedBy(),
                deviceInventory.getCreateProgram(),
                deviceInventory.getCreateDatetime(),
                deviceInventory.getUpdatedBy(),
                deviceInventory.getUpdateProgram(),
                deviceInventory.getUpdateDatetime()
        );
        // 엔티티를 DTO로 변환하는 로직
    }


}