package com.example.sales.service;

import ch.qos.logback.classic.Logger;
import com.example.contract.dto.ContractInfoDTO;
import com.example.contract.entity.ContractInfoEntity;
import com.example.contract.repository.ContractInfoRepository;
import com.example.device.dto.DeviceInventoryDTO;
import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.mapper.DeviceInventoryMapper;
import com.example.device.repository.DeviceInventoryRepository;
import com.example.device.service.DeviceInventoryService;
import com.example.sales.dto.RsltDTO;
import com.example.sales.dto.SalePrssDTO;
import com.example.sales.entity.SaleInfoEntity;
import com.example.sales.repository.SaleInfoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalePrssService {
    private final SaleInfoRepository saleInfoRepository;
    private final DeviceInventoryRepository deviceInventoryRepository;
    private final ContractInfoRepository contractInfoRepository;

    private final DeviceInventoryService deviceInventoryService;

    private final DeviceInventoryMapper deviceInventoryMapper;

    public SalePrssService(SaleInfoRepository saleInfoRepository, DeviceInventoryRepository deviceInventoryRepository, ContractInfoRepository contractInfoRepository, DeviceInventoryService deviceInventoryService, DeviceInventoryMapper deviceInventoryMapper) {
        this.saleInfoRepository = saleInfoRepository;
        this.deviceInventoryRepository = deviceInventoryRepository;
        this.contractInfoRepository = contractInfoRepository;
        this.deviceInventoryService = deviceInventoryService;
        this.deviceInventoryMapper = deviceInventoryMapper;
    }

    public RsltDTO salePrss (SalePrssDTO salePrssDTO) throws Exception {
        RsltDTO rslt = new RsltDTO();
        LocalDateTime now = LocalDateTime.now();

        try{
            // 단말재고정보 테이블 상태 update
            Optional<DeviceInventoryDTO> deviceInventoryDTO =
                    deviceInventoryService.findByCodeAndNumber(salePrssDTO.getDeviceCode(), salePrssDTO.getDeviceNumber());

            if (deviceInventoryDTO.isPresent()) {
                DeviceInventoryDTO deviceInventory = deviceInventoryDTO.get();
                deviceInventoryRepository.updateDeviceUsage(deviceInventory.getDeviceCode(), deviceInventory.getDeviceNumber(), "Y");
            }

            // 계약정보 update
            Optional<ContractInfoDTO> contractInfoDTOs = contractInfoRepository.findBySubscriptionId(salePrssDTO.getSubscriptionId())
                    .map(this::convertToDTO);

            if (contractInfoDTOs.isPresent()) {
                ContractInfoDTO contractInfoDTO = contractInfoDTOs.get();
                contractInfoRepository.updateContractInfo(salePrssDTO.getSubscriptionId(),
                        salePrssDTO.getDeviceCode(),
                        salePrssDTO.getDeviceNumber(),
                        salePrssDTO.getPlanCode(),
                        now);
            }

            // 판매정보 update
            SaleInfoEntity saleInfo = new SaleInfoEntity();
            saleInfo.setSaleId(UUID.randomUUID().toString());
            saleInfo.setSubscriptionId(salePrssDTO.getSubscriptionId());
            saleInfo.setSaleDatetime(now);
            saleInfo.setDeviceCode(salePrssDTO.getDeviceCode());
            saleInfo.setDeviceNumber(salePrssDTO.getDeviceNumber());
            saleInfo.setDevicePrice(salePrssDTO.getDevicePrice());
            saleInfo.setSupportAmount(salePrssDTO.getSupportAmount());
            saleInfo.setSaleAmount(salePrssDTO.getSaleAmount());
            saleInfo.setContractStartDatetime(now);
            saleInfo.setContractEndDatetime(now.plusMonths(24));
            saleInfo.setCreatedBy("hsjung03");
            saleInfo.setCreateProgram("salePrss");
            saleInfo.setCreateDatetime(now);

            saleInfoRepository.save(saleInfo);

            rslt.setRsltMsg("판매성공");
            rslt.setRsltCd("Y");
        } catch(Exception e){
            throw new Exception("판매에 실패하였습니다.", e);
        }
        return rslt;
    }

    private ContractInfoDTO convertToDTO(ContractInfoEntity contractInfoEntity) {

        return new ContractInfoDTO(
                contractInfoEntity.getContractId(),
                contractInfoEntity.getSubscriptionId(),
                contractInfoEntity.getDeviceCode(),
                contractInfoEntity.getDeviceNumber(),
                contractInfoEntity.getPlanCode(),
                contractInfoEntity.getContractDatetime(),
                contractInfoEntity.getCreatedBy(),
                contractInfoEntity.getCreateProgram(),
                contractInfoEntity.getCreateDatetime(),
                contractInfoEntity.getUpdatedBy(),
                contractInfoEntity.getUpdateProgram(),
                contractInfoEntity.getUpdateDatetime()
        );
    }
}
