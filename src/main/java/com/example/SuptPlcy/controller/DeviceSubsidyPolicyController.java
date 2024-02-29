package com.example.SuptPlcy.controller;

import com.example.SuptPlcy.dto.DeviceSubsidyPolicyDTO;
import com.example.SuptPlcy.entity.DeviceSubsidyPolicyEntity;
import com.example.SuptPlcy.service.DeviceSubsidyPolicyService;
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

}