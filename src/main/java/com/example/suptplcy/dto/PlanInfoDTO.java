package com.example.SuptPlcy.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PlanInfoDTO {
    private String planCode;
    private String planName;
    private String createdBy;
    private String createProgram;
    private LocalDateTime createDatetime;
    private String updatedBy;
    private String updateProgram;
    private LocalDateTime updateDatetime;

    public PlanInfoDTO(String planCode, String planName, String createdBy, String createProgram, LocalDateTime createDatetime, String updatedBy, String updateProgram, LocalDateTime updateDatetime) {
        this.planCode = planCode;
        this.planName = planName;
        this.createdBy = createdBy;
        this.createProgram = createProgram;
        this.createDatetime = createDatetime;
        this.updatedBy = updatedBy;
        this.updateProgram = updateProgram;
        this.updateDatetime = updateDatetime;
    }
}
