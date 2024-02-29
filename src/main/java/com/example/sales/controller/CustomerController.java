package com.example.sales.controller;

import com.example.sales.dto.CustomerDTO;
import com.example.sales.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @class      : 고객 정보 controller
 * @작성자      : 이정호
 * @최초작성일자 : 2024-02-07
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * @name        : 고객정보 조회 by 전화번호
     * @작성자       : 이정호
     * @수정자       : 권유리
     * @최초작성일자  : 2024-02-07
     * @수정일자     : 2024-02-29
     * @return
     */
    @GetMapping("/checkCustomer")
    public CustomerDTO checkCustomerByPhone(@RequestParam String phone) {
        return customerService.checkCustomerByPhone(phone);
    }
}
