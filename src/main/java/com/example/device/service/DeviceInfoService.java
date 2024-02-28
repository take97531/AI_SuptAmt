package com.example.device.service;

import com.example.device.dto.DeviceInfoDTO;
import com.example.device.entity.DeviceInfoEntity;
import com.example.device.repository.DeviceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceInfoService {

    private final DeviceInfoRepository repository;

    @Autowired
    public DeviceInfoService(DeviceInfoRepository repository) {
        this.repository = repository;
    }

    /**
     * @name    : 모델 정보 전체 조회 함수
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     * */
    public List<DeviceInfoDTO> findAllDevices() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * @name    : entity → DTO 변환 함수
     * @생성자   : 권유리
     * @생성일자  : 2024-02-28
     * */
    private DeviceInfoDTO convertToDto(DeviceInfoEntity deviceInfo) {
        return new DeviceInfoDTO(
                deviceInfo.getDeviceCode(),
                deviceInfo.getDeviceName(),
                deviceInfo.getDevicePrice(),
                deviceInfo.getCreatedBy(),
                deviceInfo.getCreateProgram(),
                deviceInfo.getCreateDatetime(),
                deviceInfo.getUpdatedBy(),
                deviceInfo.getUpdateProgram(),
                deviceInfo.getUpdateDatetime()
        );
    }
}
