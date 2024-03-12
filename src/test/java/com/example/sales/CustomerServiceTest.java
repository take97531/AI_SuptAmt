package com.example.sales;

import com.example.sales.dto.CustomerDTO;
import com.example.sales.entity.SubscriptionInfoEntity;
import com.example.sales.mapper.CustomerMapper;
import com.example.sales.repository.SubscriptionInfoRepository;
import com.example.sales.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
    public class CustomerServiceTest {

        @Mock
        private SubscriptionInfoRepository subscriptionInfoRepository;

        @Mock
        private CustomerMapper customerMapper;

        @Autowired
        private CustomerService customerService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }
    /**
     * @name        : CustomerService.checkCustomerByPhone => 가입정보조회
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
        @Test
        void testCheckCustomerByPhoneWhenCustomerExists() {
            String phone = "01012345678";

            CustomerDTO result = customerService.checkCustomerByPhone(phone);

            assertEquals(result.getSubscriptionId(), "efe994b8-d601-11ee-8e41-6045bd46bd25");
            assertEquals(result.getMarketCode(), "LGU+");

        }
    /**
     * @name        : CustomerService.checkCustomerByPhone => 가입정보조회(정보미존재)
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
        @Test
        void testCheckCustomerByPhoneWhenCustomerDoesNotExist() {
            String phone = "nonexistent";

            CustomerDTO result = customerService.checkCustomerByPhone(phone);

            assertNull(result);
        }
    }
