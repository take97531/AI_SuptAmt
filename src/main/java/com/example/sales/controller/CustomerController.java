package com.example.sales.controller;

import com.example.sales.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/checkCustomer")
    public boolean checkCustomerByPhone(@RequestParam String phone) {
        return customerService.checkCustomerByPhone(phone);
    }
}
