package com.example.device.controller;

import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.service.DeviceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @class   : 모델 재고 controller
 * @생성자   : 권유리
 * @생성일자 : 2024-02-28
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceInventoryController {

    private final DeviceInventoryService service;

    @Autowired
    public DeviceInventoryController(DeviceInventoryService service) {
        this.service = service;
    }

    /**
     * @name    : 모델 재고 전체 조회 controller
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     */
    @GetMapping
    public List<DeviceInventoryEntity> getAllDevices() {
        return service.findAllDevices();
    }
}
