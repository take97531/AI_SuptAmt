package com.example.SuptPlcy.entity;

import com.example.SuptPlcy.dto.DeviceSubsidyPolicyDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceSubsidyPolicyMapper {
    List<DeviceSubsidyPolicyDTO> toDeviceSubsidyPolicyDtoList(List<DeviceSubsidyPolicyEntity> deviceSubsidyPolicyEntities);
    DeviceSubsidyPolicyEntity toDeviceSubsidyPolicyEntity(DeviceSubsidyPolicyDTO deviceSubsidyPolicyDTO);
}
