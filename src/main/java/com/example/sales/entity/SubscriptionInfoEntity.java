package com.example.sales.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SUBSCRIPTION_INFO")
@Getter
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
}
