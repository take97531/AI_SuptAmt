package com.example.SuptPlcy.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Device_Info")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
public class DeviceInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEVICE_INFO_ID")
    private Long deviceInfoId;

    @Column(name = "DEVICE_CODE")
    private String deviceCode;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "DEVICE_PRICE")
    private Double devicePrice;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATE_PROGRAM")
    private String createProgram;

    @Column(name = "CREATE_DATETIME")
    private LocalDateTime createDateTime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATE_PROGRAM")
    private String updateProgram;

    @Column(name = "UPDATE_DATETIME")
    private LocalDateTime updateDateTime;
}
