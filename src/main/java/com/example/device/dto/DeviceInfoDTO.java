package com.example.device.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @class   : 모델 정보 DTO
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
public class DeviceInfoDTO {
    private String deviceCode;
    private String deviceName;
    private BigDecimal devicePrice;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;


    public DeviceInfoDTO(String deviceCode, String deviceName, BigDecimal devicePrice, String createdBy, String createProgram, LocalDateTime createDatetime, String updatedBy, String updateProgram, LocalDateTime updateDatetime) {
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.devicePrice = devicePrice;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }
}
