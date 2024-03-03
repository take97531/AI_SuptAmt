package com.example.device.mapper;

import com.example.device.dto.DeviceInventoryDTO;
import com.example.device.entity.DeviceInventoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceInventoryMapper {
    DeviceInventoryEntity toDeviceInventoryEntity(DeviceInventoryDTO deviceInventoryDTO);
}
