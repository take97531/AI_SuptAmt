package com.example.sales.repository;

import com.example.sales.dto.SalePrssDTO;
import com.example.sales.entity.SaleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface SaleInfoRepository extends JpaRepository<SaleInfoEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO SALE_INFO (subscriptionId, saleDatetime, deviceCode, deviceNumber, devicePrice,supportAmount, saleAmount, contractStartDatetime, contractEndDatetime, createdBy, createProgram, createDatetime) VALUES (:subscriptionId, :saleDatetime, :deviceCode, :deviceNumber, :devicePrice, :supportAmount, :saleAmount, :contractStartDatetime, :contractEndDatetime, :createdBy, :createProgram, :createDatetime)", nativeQuery = true)
    void insertSaleInfo(@Param("subscriptionId") String subscriptionId,
                        @Param("saleDatetime") LocalDateTime saleDatetime,
                        @Param("deviceCode") String deviceCode,
                        @Param("deviceNumber") String deviceNumber,
                        @Param("devicePrice") BigDecimal devicePrice,
                        @Param("supportAmount") BigDecimal supportAmount,
                        @Param("saleAmount") BigDecimal saleAmount,
                        @Param("contractStartDatetime") LocalDateTime contractStartDatetime,
                        @Param("contractEndDatetime") LocalDateTime contractEndDatetime,
                        @Param("createdBy") String createdBy,
                        @Param("createProgram") String createProgram,
                        @Param("createDatetime") LocalDateTime createDatetime);
}