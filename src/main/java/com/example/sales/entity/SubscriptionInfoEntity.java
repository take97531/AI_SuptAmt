package com.example.sales.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SUBSCRIPTION_INFO")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
public class SubscriptionInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUBSCRIPTION_ID")
    private String subscriptionId;

    @Column(name = "MARKET_CODE")
    private String marketCode;

    @Column(name = "SUBSCRIPTION_DATETIME")
    private LocalDateTime subscriptionDatetime;

    @Column(name = "SUBSCRIPTION_PHONE", unique = true)
    private String subscriptionPhone;

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

    // Getters and Setters

    @Builder
    public SubscriptionInfoEntity(String subscriptionId, String marketCode, LocalDateTime subscriptionDatetime,
                                  String subscriptionPhone,
                                  String createdBy, String createProgram, LocalDateTime createDatetime,
                              String updatedBy, String updateProgram, LocalDateTime updateDatetime) {
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
