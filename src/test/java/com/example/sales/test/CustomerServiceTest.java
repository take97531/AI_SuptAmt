package com.example.sales.test;

import com.example.sales.dto.CustomerDTO;
import com.example.sales.entity.SubscriptionInfoEntity;
import com.example.sales.mapper.CustomerMapper;
import com.example.sales.repository.SubscriptionInfoRepository;
import com.example.sales.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    public class CustomerServiceTest {

        @Mock
        private SubscriptionInfoRepository subscriptionInfoRepository;

        @Mock
        private CustomerMapper customerMapper;

        @InjectMocks
        private CustomerService customerService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testCheckCustomerByPhoneWhenCustomerExists() {
            String phone = "01012345678";
            SubscriptionInfoEntity subscriptionInfoEntity = new SubscriptionInfoEntity();
            CustomerDTO expectedCustomerDTO = new CustomerDTO();

            when(subscriptionInfoRepository.findBySubscriptionPhone(phone)).thenReturn(subscriptionInfoEntity);
            when(customerMapper.toPlanInfoDto(subscriptionInfoEntity)).thenReturn(expectedCustomerDTO);

            CustomerDTO result = customerService.checkCustomerByPhone(phone);

            assertEquals(expectedCustomerDTO, result);
            verify(subscriptionInfoRepository).findBySubscriptionPhone(phone);
            verify(customerMapper).toPlanInfoDto(subscriptionInfoEntity);
        }

        @Test
        void testCheckCustomerByPhoneWhenCustomerDoesNotExist() {
            String phone = "nonexistent";
            when(subscriptionInfoRepository.findBySubscriptionPhone(phone)).thenReturn(null);

            CustomerDTO result = customerService.checkCustomerByPhone(phone);

            assertNull(result);
            verify(subscriptionInfoRepository).findBySubscriptionPhone(phone);
            verify(customerMapper, never()).toPlanInfoDto(any(SubscriptionInfoEntity.class));
        }
    }
