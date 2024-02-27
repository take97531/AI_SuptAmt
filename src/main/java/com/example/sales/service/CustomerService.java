package com.example.sales.service;

import com.example.sales.entity.SubscriptionInfoEntity;
import com.example.sales.mapper.CustomerMapper;
import com.example.sales.repository.SubscriptionInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final SubscriptionInfoRepository subscriptionInfoRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(SubscriptionInfoRepository subscriptionInfoRepository, CustomerMapper customerMapper) {
        this.subscriptionInfoRepository = subscriptionInfoRepository;
        this.customerMapper = customerMapper;
    }

    public boolean checkCustomerByPhone(String phone) {
        System.out.println("phone : " + phone);
        SubscriptionInfoEntity subscriptionInfo = subscriptionInfoRepository.findBySubscriptionPhone(phone);
        System.out.println("subscriptionInfo : " + subscriptionInfo);

      //  CustomerDTO customerDto = customerMapper.toPlanInfoDto(subscriptionInfo);


      //  System.out.println("customerDto : " + customerDto);

        if(subscriptionInfo != null)
            return true;
        else
            return false;
    }
}
