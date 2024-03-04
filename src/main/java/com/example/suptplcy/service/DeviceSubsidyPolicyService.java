package com.example.suptplcy.service;

import com.example.device.entity.DeviceInfoEntity;
import com.example.device.repository.DeviceInfoRepository;
import com.example.suptplcy.dto.DeviceSubsidyPolicyDTO;
import com.example.suptplcy.entity.DeviceSubsidyPolicyEntity;
import com.example.suptplcy.entity.DeviceSubsidyPolicyMapper;
import com.example.suptplcy.repository.DeviceSubsidyPolicyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service("DeviceSupportPlcyService")
@RequiredArgsConstructor
public class DeviceSubsidyPolicyService {
    private final DeviceSubsidyPolicyRepository deviceSubsidyPolicyRepository;
    private final DeviceSubsidyPolicyMapper deviceSubsidyPolicyMapper;
    private final DeviceInfoRepository deviceInfoRepository;

    public List<DeviceSubsidyPolicyEntity> getSubsidyByDeviceAndPlan(String deviceCode, String planCode) {
        return deviceSubsidyPolicyRepository.findByDeviceCodeAndPlanCode(deviceCode, planCode);
    }

    public void createDeviceSubsidyPolicy(DeviceSubsidyPolicyDTO policyDTO) {
        DeviceSubsidyPolicyEntity policy = new DeviceSubsidyPolicyEntity();

        policy.setMarketCode("LGT");
        policy.setStartDatetime(policyDTO.getStartDatetime());
        policy.setEndDatetime(policyDTO.getEndDatetime());
        policy.setDiscountType(policyDTO.getDiscountType());
        policy.setDeviceCode(policyDTO.getDeviceCode());

        // DeviceInfo에서 출고가 가져오기
        DeviceInfoEntity deviceInfoEntity = deviceInfoRepository.findById(policyDTO.getDeviceCode()).orElse(null);
        if (deviceInfoEntity != null) {
            double devicePriceDouble = deviceInfoEntity.getDevicePrice().doubleValue();
            policy.setDeviceAmount(devicePriceDouble);
        } else {
            // DeviceInfo가 없을 경우에 대한 예외 처리
            throw new RuntimeException("DeviceInfo not found for deviceId: " + policyDTO.getDeviceCode());
        }

        policy.setSupportAmount(policyDTO.getSupportAmount());
        policy.setPlanCode(policyDTO.getPlanCode());
        policy.setCreatedBy("test");
        policy.setCreateProgram("test"); // 생성 프로그램을 "test"로 설정
        policy.setCreateDatetime(LocalDateTime.now());
        // 업데이트 관련 정보들도 "test"나 임의의 날짜로 설정
        policy.setUpdatedBy("test");
        policy.setUpdateProgram("test");
        policy.setUpdateDatetime(LocalDateTime.now());
        deviceSubsidyPolicyRepository.save(policy);
    }


    /**
     * @name        : 단말 지원금 정보 조회 by 단말모델코드, 요금제코드
     * @작성자       : 권유리
     * @최초작성일자  : 2024-03-03
     * @return      : 단말지원금정책DTO
     */
    public List<DeviceSubsidyPolicyDTO> getSubsidyByDeviceAndPlanDTO(String deviceCode, String planCode) {
        List<DeviceSubsidyPolicyEntity> entities = deviceSubsidyPolicyRepository.findByDeviceCodeAndPlanCode(deviceCode, planCode);
        return entities.stream()
                .map(this::mapEntityToDTO) // Entity를 DTO로 변환
                .collect(Collectors.toList());
    }

    /**
     * @name        : entity → dto 변환
     * @작성자       : 권유리
     * @최초작성일    : 2024-03-03
     * @return      : 단말지원금정책DTO
     */
    private DeviceSubsidyPolicyDTO mapEntityToDTO(DeviceSubsidyPolicyEntity entity) {
        return DeviceSubsidyPolicyDTO.builder()
                .deviceSubsidyPolicyId(entity.getDeviceSubsidyPolicyId())
                .marketCode(entity.getMarketCode())
                .startDatetime(entity.getStartDatetime())
                .endDatetime(entity.getEndDatetime())
                .discountType(entity.getDiscountType())
                .deviceCode(entity.getDeviceCode())
                .supportAmount(entity.getSupportAmount())
                .planCode(entity.getPlanCode())
                .createdBy(entity.getCreatedBy())
                .createProgram(entity.getCreateProgram())
                .createDatetime(entity.getCreateDatetime())
                .updatedBy(entity.getUpdatedBy())
                .updateProgram(entity.getUpdateProgram())
                .updateDatetime(entity.getUpdateDatetime())
                .build();
    }

}