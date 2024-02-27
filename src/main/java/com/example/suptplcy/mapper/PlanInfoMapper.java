package com.example.suptplcy.mapper;

import com.example.suptplcy.dto.PlanInfoDTO;
import com.example.suptplcy.entity.PlanInfoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanInfoMapper {
    List<PlanInfoDTO> toPlanInfoDtoList(List<PlanInfoEntity> planInfoEntitys);
}
