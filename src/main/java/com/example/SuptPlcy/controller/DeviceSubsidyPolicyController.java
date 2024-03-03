package com.example.suptplcy.controller;

import com.example.suptplcy.dto.DeviceSubsidyPolicyDTO;
import com.example.suptplcy.entity.DeviceSubsidyPolicyEntity;
import com.example.suptplcy.service.DeviceSubsidyPolicyService;
import com.example.suptplcy.dto.DeviceSubsidyPolicyDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
@Slf4j
public class DeviceSubsidyPolicyController {
    private final DeviceSubsidyPolicyService deviceSubsidyPolicyService;

    @GetMapping("/subsidy/{deviceCode}/{planCode}")
    public List<DeviceSubsidyPolicyEntity> getSubsidyByDeviceAndPlan(@PathVariable String deviceCode,
            @PathVariable String planCode) {
        return deviceSubsidyPolicyService.getSubsidyByDeviceAndPlan(deviceCode, planCode);
    }

    @PostMapping("/subsidy")
    public void createDeviceSubsidyPolicy(@RequestBody DeviceSubsidyPolicyDTO policyDTO) {
        deviceSubsidyPolicyService.createDeviceSubsidyPolicy(policyDTO);
    }


    /**
     * @name        : 단말 지원금 정보 조회 by 단말모델코드, 요금제코드
     * @작성자       : 권유리
     * @최초작성일자  : 2024-03-03
     * @return     : 단말지원금정책DTO
     */
    @GetMapping("/subsidydto/{deviceCode}/{planCode}")
    public List<DeviceSubsidyPolicyDTO> getSubsidyByDeviceAndPlanDTO(@PathVariable String deviceCode,
                                                                     @PathVariable String planCode) {
        return deviceSubsidyPolicyService.getSubsidyByDeviceAndPlanDTO(deviceCode, planCode);
    }

}