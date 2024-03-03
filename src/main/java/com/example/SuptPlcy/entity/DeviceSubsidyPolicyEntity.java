package com.example.suptplcy.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "DEVICE_SUBSIDY_POLICY")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
public class DeviceSubsidyPolicyEntity {
    @Id
    @Column(name = "DEVICE_SUBSIDY_POLICY_ID", nullable = false)
    private int deviceSubsidyPolicyId;

    @Column(name = "MARKET_CODE", nullable = false)
    private String marketCode;

    @Column(name = "START_DATETIME")
    private LocalDateTime startDatetime;

    @Column(name = "END_DATETIME")
    private LocalDateTime endDatetime;

    @Column(name = "DISCOUNT_TYPE")
    private String discountType;

    @Column(name = "DEVICE_CODE")
    private String deviceCode;

    @Column(name = "SUPPORT_AMOUNT")
    private double supportAmount;

    @Column(name = "PLAN_CODE")
    private String planCode;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATE_PROGRAM", nullable = false)
    private String createProgram;

    @Column(name = "CREATE_DATETIME", nullable = false)
    private LocalDateTime createDatetime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATE_PROGRAM")
    private String updateProgram;

    @Column(name = "UPDATE_DATETIME")
    private LocalDateTime updateDatetime;


}