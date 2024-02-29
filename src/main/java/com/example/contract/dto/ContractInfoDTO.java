package com.example.contract.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ContractInfoDTO {
    private String contractId;
    private String subscriptionId;
    private String deviceCode;
    private String deviceNumber;
    private String planCode;
    private LocalDateTime contractDatetime;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

    public ContractInfoDTO(String contractId, String subscriptionId, String deviceCode,
                              String deviceNumber, String planCode, LocalDateTime contractDatetime,
                              String createdBy, String createProgram, LocalDateTime createDatetime,
                              String updatedBy, String updateProgram, LocalDateTime updateDatetime) {
        this.contractId = contractId;
        this.subscriptionId = subscriptionId;
        this.deviceCode = deviceCode;
        this.deviceNumber = deviceNumber;
        this.planCode = planCode;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }
}
