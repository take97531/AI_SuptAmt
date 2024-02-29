package com.example.sales.service;

import com.example.contract.entity.ContractInfoEntity;
import com.example.contract.repository.ContractInfoRepository;
import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.repository.DeviceInventoryRepository;
import com.example.sales.dto.SalePrssDTO;
import com.example.sales.entity.SaleInfoEntity;
import com.example.sales.entity.SubscriptionInfoEntity;
import com.example.sales.repository.SaleInfoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SalePrssService {
    private final SaleInfoRepository saleInfoRepository;
    private final DeviceInventoryRepository deviceInventoryRepository;
    private final ContractInfoRepository contractInfoRepository;

    public SalePrssService(SaleInfoRepository saleInfoRepository, DeviceInventoryRepository deviceInventoryRepository, ContractInfoRepository contractInfoRepository) {
        this.saleInfoRepository = saleInfoRepository;
        this.deviceInventoryRepository = deviceInventoryRepository;
        this.contractInfoRepository = contractInfoRepository;
    }

    public void salePrss(SalePrssDTO salePrssDTO){
        // 단말재고정보 테이블 상태 update
        Optional<DeviceInventoryEntity> optionalDeviceInventory = deviceInventoryRepository.findByDeviceCodeAndDeviceNumber(salePrssDTO.getDeviceCode(), salePrssDTO.getDeviceNumber());
        if (optionalDeviceInventory.isPresent()) {
            DeviceInventoryEntity deviceInventory = optionalDeviceInventory.get();
            deviceInventory.setDeviceUsage("Y");
            deviceInventoryRepository.save(deviceInventory);
        }

        // 계약정보 update
        Optional<ContractInfoEntity> optionalSubscriptionInfo = contractInfoRepository.findBySubscriptionId(salePrssDTO.getSubscriptionId());
        if (optionalSubscriptionInfo.isPresent()) {
            ContractInfoEntity subscriptionInfo = optionalSubscriptionInfo.get();
            subscriptionInfo.setDeviceCode(salePrssDTO.getDeviceCode());
            subscriptionInfo.setDeviceNumber(salePrssDTO.getDeviceNumber());
            subscriptionInfo.setPlanCode(salePrssDTO.getPlanCode());
            subscriptionInfo.setContractDatetime(LocalDateTime.now());
            contractInfoRepository.save(subscriptionInfo);
        }

        // 판매정보 update
        SaleInfoEntity saleInfo = new SaleInfoEntity();
        saleInfo.setSaleId("123456789");
        saleInfo.setSubscriptionId(salePrssDTO.getSubscriptionId());
        saleInfo.setSaleDatetime(LocalDateTime.now());
        saleInfo.setDeviceCode(salePrssDTO.getDeviceCode());
        saleInfo.setDeviceNumber(salePrssDTO.getDeviceNumber());
        saleInfo.setDevicePrice(salePrssDTO.getDevicePrice());
        saleInfo.setSupportAmount(salePrssDTO.getSupportAmount());
        saleInfo.setSaleAmount(salePrssDTO.getSaleAmount());
        saleInfo.setContractStartDatetime(LocalDateTime.now());
        saleInfo.setContractEndDatetime(LocalDateTime.now().plusMonths(24));

        saleInfoRepository.save(saleInfo);
    }
}