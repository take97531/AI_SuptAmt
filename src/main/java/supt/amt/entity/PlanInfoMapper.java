package supt.amt.entity;

import org.mapstruct.Mapper;
import supt.amt.dto.PlanInfoDTO;

import java.util.List;

@Mapper
public interface PlanInfoMapper {
    List<PlanInfoDTO> toPlanInfoDtoList(List<PlanInfoEntity> planInfoEntitys);
}
