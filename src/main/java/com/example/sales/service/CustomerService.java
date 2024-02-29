package com.example.sales.service;

import com.example.sales.dto.CustomerDTO;
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

    /**
     * @name        : 전화번호로 고객정보 조회 api
     * @작성자       : 이정호
     * @수정자       : 권유리
     * @최초작성일자  : 2024-02-07
     * @수정일자     : 2024-02-29
     * @return
     */
    public CustomerDTO checkCustomerByPhone(String phone) {
        SubscriptionInfoEntity subscriptionInfo = subscriptionInfoRepository.findBySubscriptionPhone(phone);
        if(subscriptionInfo != null) {
            return customerMapper.toPlanInfoDto(subscriptionInfo);
        } else {
            return null;
        }
    }
}
