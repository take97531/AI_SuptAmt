package supt.amt.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import supt.amt.dto.PlanInfoDTO;
import supt.amt.entity.PlanInfoMapper;
import supt.amt.repository.PlanInfoRepositoryImpl;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanInfoService {
    PlanInfoRepositoryImpl planInfoRepository;
    PlanInfoMapper mapper;

    @Transactional
    public List<PlanInfoDTO> getPlanInfo() {
        return mapper.toPlanInfoDtoList(planInfoRepository.findAll());
    }
}
