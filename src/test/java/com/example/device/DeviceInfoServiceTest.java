package com.example.device;

import com.example.device.dto.DeviceInfoDTO;
import com.example.device.service.DeviceInfoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeviceInfoServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Autowired
    private DeviceInfoService service; // YourService를 service의 실제 타입으로 변경하세요.

    /**
     * @name        : DeviceInfoService.findAllDevices => 단말정보 조회
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
    @Test
    public void findAllDevices() {
        List<DeviceInfoDTO> result = service.findAllDevices();

        assertEquals(result.get(0).getDeviceCode(), "A3102-256");
        assertEquals(result.get(0).getDeviceName(), "iPhone15 Pro_256");
        assertEquals(result.get(1).getDeviceCode(), "SM-F711N");
        assertEquals(result.get(1).getDeviceName(), "갤럭시 Z Flip 3");
        assertEquals(result.get(2).getDeviceCode(), "SM-F946N512");
        assertEquals(result.get(2).getDeviceName(), "갤럭시 Z Fold 5 512GB");
        assertEquals(result.get(3).getDeviceCode(), "SM-G996N");
        assertEquals(result.get(3).getDeviceName(), "갤럭시 S21+");
        assertEquals(result.get(4).getDeviceCode(), "SM-N960N");
        assertEquals(result.get(4).getDeviceName(), "갤럭시 노트9");
    }
}