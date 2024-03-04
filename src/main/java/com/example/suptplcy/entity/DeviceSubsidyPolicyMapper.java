package com.example.suptplcy.entity;

import com.example.suptplcy.dto.DeviceSubsidyPolicyDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceSubsidyPolicyMapper {
    List<DeviceSubsidyPolicyDTO> toDeviceSubsidyPolicyDtoList(List<DeviceSubsidyPolicyEntity> deviceSubsidyPolicyEntities);
    DeviceSubsidyPolicyEntity toDeviceSubsidyPolicyEntity(DeviceSubsidyPolicyDTO deviceSubsidyPolicyDTO);
}
