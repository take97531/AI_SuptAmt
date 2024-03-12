package com.example.device;

import com.example.device.dto.DeviceInventoryDTO;
import com.example.device.entity.DeviceInventoryEntity;
import com.example.device.repository.DeviceInventoryRepository;
import com.example.device.service.DeviceInventoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeviceInventoryServiceTest {

    @Mock
    private DeviceInventoryRepository repository; // YourRepository를 repository의 실제 타입으로 변경하세요.

    @Autowired
    private DeviceInventoryService service; // YourService를 service의 실제 타입으로 변경하세요.

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.initMocks(this);
    }
    /**
     * @name        : DeviceInventoryService.findByCodeAndNumber => 단말재고정보 조회
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
    @Test
    public void findByCodeAndNumber() {
        String deviceCode = "SM-F711N";
        String deviceNumber = "98765";

        Optional<DeviceInventoryDTO> result = service.findByCodeAndNumber(deviceCode, deviceNumber);

        assertEquals(result.get().getDeviceCode(), "SM-F711N");
        assertEquals(result.get().getDeviceNumber(), "98765");

    }
    /**
     * @name        : DeviceInventoryService.findByCodeAndNumber => 단말재고정보 조회(미존재)
     * @작성자       : 유안수
     * @수정자       :
     * @최초작성일자  : 2024-03-12
     * @수정일자     :
     * @return
     */
    @Test
    public void findByCodeAndNumberNotExist() {
        String deviceCode = "@SM-F711N@";
        String deviceNumber = "98765";

        Optional<DeviceInventoryDTO> result = service.findByCodeAndNumber(deviceCode, deviceNumber);

        assertEquals(result, Optional.empty());

    }
}