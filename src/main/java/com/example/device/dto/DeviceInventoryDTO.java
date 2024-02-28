package com.example.device.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @class   : 모델 재고 DTO
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
@Data
@Builder
public class DeviceInventoryDTO {

    private String deviceCode;
    private String deviceNumber;
    private String deviceUsage;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

    public DeviceInventoryDTO(String deviceCode, String deviceNumber, String deviceUsage,
                              String createdBy, String createProgram, LocalDateTime createDatetime,
                              String updatedBy, String updateProgram, LocalDateTime updateDatetime) {
        this.deviceCode = deviceCode;
        this.deviceNumber = deviceNumber;
        this.deviceUsage = deviceUsage;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }

}
