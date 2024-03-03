package com.example.sales.service;

import ch.qos.logback.classic.Logger;
import com.example.contract.entity.ContractInfoEntity;
import com.example.contract.repository.ContractInfoRepository;
import com.example.device.dto.DeviceInventoryDTO;
import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.repository.DeviceInventoryRepository;
import com.example.device.service.DeviceInventoryService;
import com.example.sales.dto.RsltDTO;
import com.example.sales.dto.SalePrssDTO;
import com.example.sales.entity.SaleInfoEntity;
import com.example.sales.repository.SaleInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SalePrssService {
    private final SaleInfoRepository saleInfoRepository;
    private final DeviceInventoryRepository deviceInventoryRepository;
    private final ContractInfoRepository contractInfoRepository;

    private final DeviceInventoryService deviceInventoryService;

    public SalePrssService(SaleInfoRepository saleInfoRepository, DeviceInventoryRepository deviceInventoryRepository, ContractInfoRepository contractInfoRepository, DeviceInventoryService deviceInventoryService) {
        this.saleInfoRepository = saleInfoRepository;
        this.deviceInventoryRepository = deviceInventoryRepository;
        this.contractInfoRepository = contractInfoRepository;
        this.deviceInventoryService = deviceInventoryService;
    }

    public RsltDTO salePrss (SalePrssDTO salePrssDTO) throws Exception {
        RsltDTO rslt = new RsltDTO();
        LocalDateTime now = LocalDateTime.now();

        try{
            // 단말재고정보 테이블 상태 update
            /*Optional<DeviceInventoryEntity> optionalDeviceInventory = deviceInventoryRepository.findByDeviceCodeAndDeviceNumber(salePrssDTO.getDeviceCode(), salePrssDTO.getDeviceNumber());
            if (optionalDeviceInventory.isPresent()) {
                DeviceInventoryEntity deviceInventory = optionalDeviceInventory.get();
                deviceInventory.setDeviceUsage("Y");
                deviceInventoryRepository.save(deviceInventory);
            }*/

            Optional<DeviceInventoryDTO> deviceInventoryDTO =
                    deviceInventoryService.findByCodeAndNumber(salePrssDTO.getDeviceCode(), salePrssDTO.getDeviceNumber());

            if (deviceInventoryDTO.isPresent()) {
                throw new Exception("물류정보 조회 성공 : " + deviceInventoryDTO.get().getDeviceCode());
                //DeviceInventoryDTO deviceInventory = deviceInventoryDTO.get();
                //deviceInventory.setDeviceUsage("Y");
                // deviceInventoryRepository.save(DeviceInventory);
            }

            throw new Exception("물류 정보 없음.");


            /*// 계약정보 update
            Optional<ContractInfoEntity> optionalSubscriptionInfo = contractInfoRepository.findBySubscriptionId(salePrssDTO.getSubscriptionId());
            if (optionalSubscriptionInfo.isPresent()) {
                ContractInfoEntity subscriptionInfo = optionalSubscriptionInfo.get();
                subscriptionInfo.setDeviceCode(salePrssDTO.getDeviceCode());
                subscriptionInfo.setDeviceNumber(salePrssDTO.getDeviceNumber());
                subscriptionInfo.setPlanCode(salePrssDTO.getPlanCode());
                subscriptionInfo.setContractDatetime(now);
                contractInfoRepository.save(subscriptionInfo);
            }

            // 판매정보 update
            SaleInfoEntity saleInfo = new SaleInfoEntity();
            saleInfo.setSubscriptionId(salePrssDTO.getSubscriptionId());
            saleInfo.setSaleDatetime(now);
            saleInfo.setDeviceCode(salePrssDTO.getDeviceCode());
            saleInfo.setDeviceNumber(salePrssDTO.getDeviceNumber());
            saleInfo.setDevicePrice(salePrssDTO.getDevicePrice());
            saleInfo.setSupportAmount(salePrssDTO.getSupportAmount());
            saleInfo.setSaleAmount(salePrssDTO.getSaleAmount());
            saleInfo.setContractStartDatetime(now);
            saleInfo.setContractEndDatetime(now.plusMonths(24));
            saleInfo.setCreatedBy("정하성");
            saleInfo.setCreateProgram("salePrss");
            saleInfo.setCreateDatetime(now);

            saleInfoRepository.save(saleInfo);

            rslt.setRsltMsg("판매성공");
            rslt.setRsltCd("Y");*/
        } catch(Exception e){
            throw new Exception("판매에 실패하였습니다.", e);
        }
    }
}
