package com.example.contract.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "CONTRACT_INFO")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
public class ContractInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public ContractInfoEntity(String contractId, String subscriptionId, String deviceCode,
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
