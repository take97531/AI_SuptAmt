package com.example.sales.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SalePrssDTO {
    private String subscriptionId;
    private String deviceCode;
    private String deviceNumber;
    private String planCode;
    private BigDecimal devicePrice;
    private BigDecimal supportAmount;
    private BigDecimal saleAmount;

    public SalePrssDTO(String subscriptionId, String deviceCode, String deviceNumber,
                       String planCode, BigDecimal devicePrice, BigDecimal supportAmount, BigDecimal saleAmount) {
        this.subscriptionId = subscriptionId;
        this.deviceCode = deviceCode;
        this.deviceNumber = deviceNumber;
        this.planCode = planCode;
        this.devicePrice = devicePrice;
        this.supportAmount = supportAmount;
        this.saleAmount = saleAmount;
    }

    public SalePrssDTO() {

    }
}
