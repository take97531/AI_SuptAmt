package com.example.sales.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerDTO {
    private String subscriptionId;
    private String marketCode;
    private LocalDateTime  subscriptionDatetime;
    private String subscriptionPhone;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

    public CustomerDTO(String subscriptionId, String marketCode, LocalDateTime subscriptionDatetime,
                       String subscriptionPhone, String createdBy, String createProgram,
                       LocalDateTime createDatetime, String updatedBy, String updateProgram,
                       LocalDateTime updateDatetime) {
        this.subscriptionId = subscriptionId;
        this.marketCode = marketCode;
        this.subscriptionDatetime = subscriptionDatetime;
        this.subscriptionPhone = subscriptionPhone;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }
}