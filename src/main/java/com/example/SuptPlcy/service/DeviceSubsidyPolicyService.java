package com.example.SuptPlcy.service;

import com.example.SuptPlcy.dto.DeviceSubsidyPolicyDTO;
import com.example.SuptPlcy.entity.DeviceSubsidyPolicyEntity;
import com.example.SuptPlcy.entity.DeviceSubsidyPolicyMapper;
import com.example.SuptPlcy.repository.DeviceSubsidyPolicyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Transactional
@Service("DeviceSupportPlcyService")
@RequiredArgsConstructor
public class DeviceSubsidyPolicyService {
    private final DeviceSubsidyPolicyRepository deviceSubsidyPolicyRepository;
    private final DeviceSubsidyPolicyMapper deviceSubsidyPolicyMapper;

    public List<DeviceSubsidyPolicyEntity> getSubsidyByDeviceAndPlan(String deviceCode, String planCode) {
        return deviceSubsidyPolicyRepository.findByDeviceCodeAndPlanCode(deviceCode, planCode);
    }

    public void createDeviceSubsidyPolicy(DeviceSubsidyPolicyDTO policyDTO) {
        DeviceSubsidyPolicyDTO policy = new DeviceSubsidyPolicyDTO();
        policy.setMarketCode(policyDTO.getMarketCode());
        policy.setStartDatetime(policyDTO.getStartDatetime());
        policy.setEndDatetime(policyDTO.getEndDatetime());
        policy.setDiscountType(policyDTO.getDiscountType());
        policy.setDeviceCode(policyDTO.getDeviceCode());
        policy.setDeviceAmount(policyDTO.getDeviceAmount());
        policy.setSupportAmount(policyDTO.getSupportAmount());
        policy.setPlanCode(policyDTO.getPlanCode());
        policy.setCreatedBy("test");
        policy.setCreateProgram("test"); // 생성 프로그램을 "test"로 설정
        policy.setCreateDatetime(LocalDateTime.now());
        // 업데이트 관련 정보들도 "test"나 임의의 날짜로 설정
        policy.setUpdatedBy("test");
        policy.setUpdateProgram("test");
        policy.setUpdateDatetime(LocalDateTime.now());
        deviceSubsidyPolicyRepository.save(deviceSubsidyPolicyMapper.toDeviceSubsidyPolicyEntity(policy));
    }

}