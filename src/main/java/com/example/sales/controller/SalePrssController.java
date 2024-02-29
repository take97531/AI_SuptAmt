package com.example.sales.controller;

import com.example.sales.dto.SalePrssDTO;
import com.example.sales.service.SalePrssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
@Slf4j
public class SalePrssController {
    @Autowired
    private SalePrssService SalePrssService;

    @PostMapping ("/presales")
    public void salePrss(@RequestBody SalePrssDTO salePrssDTO) {
        SalePrssService.salePrss(salePrssDTO);
    }
}
