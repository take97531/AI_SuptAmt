package com.example.device.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @class   : 모델 정보 Entity
 * @table   : device_info
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
@Setter
@Getter
@Entity
@Table(name = "device_info")
public class DeviceInfoEntity {

    @Id
    private String deviceCode;
    private String deviceName;
    private BigDecimal devicePrice;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;
}
