package supt.amt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supt.amt.dto.PlanInfoDTO;
import supt.amt.entity.PlanInfoMapper;
import supt.amt.repository.PlanInfoRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanInfoService {
    PlanInfoRepository planInfoRepository;
    PlanInfoMapper planInfoMapper;

    @Transactional
    public List<PlanInfoDTO> getPlanInfo() {
        return planInfoMapper.toPlanInfoDtoList(planInfoRepository.findAll());
    }
}
