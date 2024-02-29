package com.example.suptplcy.controller;

import com.example.suptplcy.dto.PlanInfoDTO;
import com.example.suptplcy.service.PlanInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plcys")
@Slf4j
public class PlanInfoController {
    @Autowired
    private final PlanInfoService planInfoService;

    // retrieveAll
    @GetMapping("/retrieveAll")
    public List<PlanInfoDTO> getPlanInfo() {

        return planInfoService.getPlanInfo();
    }
}
