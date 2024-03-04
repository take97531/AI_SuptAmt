package com.example.suptplcy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DEVICE_SUBSIDY_POLICY")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
public class DeviceSubsidyPolicyEntity {
    @Id
    @Column(name = "DEVICE_SUBSIDY_POLICY_ID", nullable = false)
    private int deviceSubsidyPolicyId;

    @Column(name = "MARKET_CODE")
    private String marketCode;

    @Column(name = "START_DATETIME")
    private LocalDateTime startDatetime;

    @Column(name = "END_DATETIME")
    private LocalDateTime endDatetime;

    @Column(name = "DISCOUNT_TYPE")
    private String discountType;

    @Column(name = "DEVICE_CODE")
    private String deviceCode;

    @Column(name = "DEVICE_AMOUNT")
    private double deviceAmount;

    @Column(name = "SUPPORT_AMOUNT")
    private double supportAmount;

    @Column(name = "PLAN_CODE")
    private String planCode;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATE_PROGRAM")
    private String createProgram;

    @Column(name = "CREATE_DATETIME")
    private LocalDateTime createDatetime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATE_PROGRAM")
    private String updateProgram;

    @Column(name = "UPDATE_DATETIME")
    private LocalDateTime updateDatetime;


}