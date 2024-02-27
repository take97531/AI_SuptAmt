package com.example.sales.mapper;

import com.example.sales.dto.CustomerDTO;
import com.example.sales.entity.SubscriptionInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toPlanInfoDto(SubscriptionInfoEntity subscriptionInfoEntity);
}
