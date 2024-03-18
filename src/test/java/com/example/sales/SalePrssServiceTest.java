package com.example.sales;

import com.example.sales.dto.RsltDTO;
import com.example.sales.dto.SalePrssDTO;
import com.example.sales.service.SalePrssService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SalePrssServiceTest {
    @Autowired
    private SalePrssService salePrssService;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void SalePrss() throws Exception {
        // 테스트 데이터 준비
        SalePrssDTO salePrssDTO = new SalePrssDTO();
        salePrssDTO.setDeviceCode("SM-G996N");
        salePrssDTO.setDeviceNumber("24680");
        salePrssDTO.setSubscriptionId("9537a6a9-d607-11ee-899e-002248cdf863");
        salePrssDTO.setPlanCode("PLAN003");
        salePrssDTO.setDevicePrice(BigDecimal.valueOf(1045000.00));
        salePrssDTO.setSupportAmount(BigDecimal.valueOf(345000.00));
        salePrssDTO.setSaleAmount(BigDecimal.valueOf(700000.00));

        // 메서드 호출
        RsltDTO result = salePrssService.salePrss(salePrssDTO);

        // 결과 검증
        assertEquals("Y", result.getRsltCd());
        assertEquals("판매성공", result.getRsltMsg());
    }
}
