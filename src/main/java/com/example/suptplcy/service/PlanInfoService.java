package com.example.suptplcy.service;

import com.example.suptplcy.dto.PlanInfoDTO;
import com.example.suptplcy.mapper.PlanInfoMapper;
import com.example.suptplcy.repository.PlanInfoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlanInfoService {
    private final PlanInfoRepository planInfoRepository;
    private final PlanInfoMapper planInfoMapper;

    public PlanInfoService(PlanInfoRepository planInfoRepository, PlanInfoMapper planInfoMapper) {
        this.planInfoRepository = planInfoRepository;
        this.planInfoMapper = planInfoMapper;
    }

    @Transactional
    public List<PlanInfoDTO> getPlanInfo() {

        return planInfoMapper.toPlanInfoDtoList(planInfoRepository.findAll());
    }
}
