package com.example.device.controller;

import com.example.device.dto.DeviceInventoryDTO;
import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.service.DeviceInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @class   : 모델 재고 controller
 * @생성자   : 권유리
 * @생성일자 : 2024-02-28
 */
@RestController
@RequestMapping("/api/v1/device/deviceinventory")
public class DeviceInventoryController {

    /* service */
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
    @GetMapping("/alldeviceinventory")
    public List<DeviceInventoryEntity> getAllDevices() {
        return service.findAllDevices();
    }

    /**
     * @name    : 단말 재고 조회 by 단말코드 , 단말번호
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     */
    @GetMapping("/device-inventory")
    public ResponseEntity<DeviceInventoryDTO> getDeviceInventory(@RequestParam("deviceCode") String deviceCode,
                                                                 @RequestParam("deviceNumber") String deviceNumber) {

        return service.findByCodeAndNumber(deviceCode, deviceNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


}
