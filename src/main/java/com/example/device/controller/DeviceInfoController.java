package com.example.device.controller;

import com.example.device.dto.DeviceInfoDTO;
import com.example.device.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @class   : 모델 정보 controller
 * @생성자   : 권유리
 * @생성일자 : 2024-02-28
 */
@RestController
@RequestMapping("/api/deviceInfos")
public class DeviceInfoController {
    private final DeviceInfoService service;

    @Autowired
    public DeviceInfoController(DeviceInfoService service) {
        this.service = service;
    }

    /**
     * @name    : 모델 정보 전체 조회 controller
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     */
    @GetMapping
    public List<DeviceInfoDTO> getAllDeviceInfos() {
        return service.findAllDevices();
    }
}
