package com.example.SuptPlcy.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DeviceSubsidyPolicyDTO {

    private int deviceSubsidyPolicyId;
    private String marketCode;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String discountType;
    private String deviceCode;
    private double deviceAmount;
    private double supportAmount;
    private String planCode;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

    public DeviceSubsidyPolicyDTO() {
        // 기본 생성자
    }

    public DeviceSubsidyPolicyDTO(int deviceSubsidyPolicyId, String marketCode, LocalDateTime startDatetime, LocalDateTime endDatetime,
    String discountType, String deviceCode, double deviceAmount, double supportAmount, String planCode, String createdBy, String createProgram, LocalDateTime createDatetime, String updatedBy,
    String updateProgram, LocalDateTime updateDatetime){
        this.deviceSubsidyPolicyId = deviceSubsidyPolicyId;
        this.marketCode = marketCode;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.discountType = discountType;
        this.deviceCode = deviceCode;
        this.deviceAmount = deviceAmount;
        this.supportAmount = supportAmount;
        this.planCode = planCode;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }
}
