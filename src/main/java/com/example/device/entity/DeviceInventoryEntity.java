package com.example.device.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @class   : 모델 재고 Entity
 * @생성자   : 권유리
 * @생성일자  : 2024-02-28
 */
@Entity
@Setter
@Getter
@Table(name = "device_inventory")
public class DeviceInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String deviceCode;
    private String deviceNumber;
    private String deviceUsage;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

}
