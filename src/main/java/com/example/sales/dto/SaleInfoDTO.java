package com.example.sales.dto;

import lombok.Data;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SaleInfoDTO {
    private String saleId;
    private String subscriptionId;
    private LocalDateTime saleDatetime;
    private String deviceCode;
    private String deviceNumber;
    private BigDecimal devicePrice;
    private BigDecimal supportAmount;
    private BigDecimal saleAmount;
    private LocalDateTime contractStartDatetime;
    private LocalDateTime contractEndDatetime;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

    public SaleInfoDTO(String saleId, String subscriptionId, LocalDateTime saleDatetime,
                       String deviceCode, String deviceNumber, BigDecimal devicePrice, BigDecimal supportAmount, BigDecimal saleAmount,
                       LocalDateTime contractStartDatetime, LocalDateTime contractEndDatetime,String createdBy, String createProgram,
                       LocalDateTime createDatetime, String updatedBy, String updateProgram,
                       LocalDateTime updateDatetime) {
        this.saleId = saleId;
        this.subscriptionId = subscriptionId;
        this.saleDatetime = saleDatetime;
        this.deviceCode = deviceCode;
        this.deviceNumber = deviceNumber;
        this.devicePrice = devicePrice;
        this.supportAmount = supportAmount;
        this.saleAmount = saleAmount;
        this.contractStartDatetime = contractStartDatetime;
        this.contractEndDatetime = contractEndDatetime;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }
}
