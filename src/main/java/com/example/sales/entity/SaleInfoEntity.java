package com.example.sales.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "SALE_INFO")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
public class SaleInfoEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "SALE_ID")
    private String saleId;

    @Column(name = "SUBSCRIPTION_ID")
    private String subscriptionId;

    @Column(name = "SALE_DATETIME")
    private LocalDateTime saleDatetime;

    @Column(name = "DEVICE_CODE")
    private String deviceCode;

    @Column(name = "DEVICE_NUMBER")
    private String deviceNumber;

    @Column(name = "DEVICE_PRICE")
    private BigDecimal devicePrice;

    @Column(name = "SUPPORT_AMOUNT")
    private BigDecimal supportAmount;

    @Column(name = "SALE_AMOUNT")
    private BigDecimal saleAmount;

    @Column(name = "CONTRACT_START_DATETIME")
    private LocalDateTime contractStartDatetime;

    @Column(name = "CONTRACT_END_DATETIME")
    private LocalDateTime contractEndDatetime;

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

    @Builder
    public SaleInfoEntity(String saleId, String subscriptionId, LocalDateTime saleDatetime,
                                  String deviceCode, String deviceNumber, BigDecimal devicePrice, BigDecimal supportAmount, BigDecimal saleAmount,
                          LocalDateTime contractStartDatetime, LocalDateTime contractEndDatetime,
                                  String createdBy, String createProgram, LocalDateTime createDatetime,
                                  String updatedBy, String updateProgram, LocalDateTime updateDatetime) {
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
