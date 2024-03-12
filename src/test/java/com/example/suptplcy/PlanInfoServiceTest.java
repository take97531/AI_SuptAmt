package com.example.suptplcy;

import com.example.suptplcy.dto.PlanInfoDTO;
import com.example.suptplcy.service.PlanInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PlanInfoServiceTest {

    @Autowired
    private PlanInfoService service;
    /**
     * @name        : PlanInfoService.getPlanInfo => 요금제정보조회
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
    @Test
    public void getPlanInfo() {
        List<PlanInfoDTO> result = service.getPlanInfo();

        assertEquals(result.get(0).getPlanCode(), "PLAN001");
        assertEquals(result.get(1).getPlanCode(), "PLAN002");
        assertEquals(result.get(2).getPlanCode(), "PLAN003");
        assertEquals(result.get(3).getPlanCode(), "PLAN004");
        assertEquals(result.get(4).getPlanCode(), "PLAN005");
    }
}