package com.example.suptplcy;

import com.example.suptplcy.dto.DeviceSubsidyPolicyDTO;
import com.example.suptplcy.service.DeviceSubsidyPolicyService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeviceSubsidyPolicyServiceTest {
    @Autowired
    private DeviceSubsidyPolicyService service; // YourService를 service의 실제 타입으로 변경하세요.

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
    }
    /**
     * @name        : DeviceSubsidyPolicyService.getSubsidyByDeviceAndPlan => 정책조회
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
    @Test
    public void getSubsidyByDeviceAndPlan() {
        String deviceCode = "A3102-256";
        String planCode = "PLAN001";

        List<DeviceSubsidyPolicyDTO> result = service.getSubsidyByDeviceAndPlanDTO(deviceCode, planCode);

        assertEquals(result.get(0).getDeviceCode(), "A3102-256");
        assertEquals(result.get(0).getPlanCode(), "PLAN001");
        assertEquals(result.get(0).getSupportAmount(), 100.0, 0.00001);
    }

    /**
     * @name        : DeviceSubsidyPolicyService.createDeviceSubsidyPolicy => 정책등록
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
    @Test
    @Transactional
    public void createDeviceSubsidyPolicy(){
        String deviceCode = "A3102-256";
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.MAX; // LocalDateTime.MAX를 실제 사용하기 적합한 값으로 변경할 수 있습니다.
        String discountType = "S";
        Double supportAmount = 1000.0;
        String planCode = "PLAN006";

        DeviceSubsidyPolicyDTO policyDTO = new DeviceSubsidyPolicyDTO();
        policyDTO.setDeviceCode(deviceCode);
        policyDTO.setStartDatetime(startDateTime);
        policyDTO.setEndDatetime(endDateTime);
        policyDTO.setDiscountType(discountType);
        policyDTO.setSupportAmount(supportAmount);
        policyDTO.setPlanCode(planCode);

        service.createDeviceSubsidyPolicy(policyDTO);
    }

}