package supt.amt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import supt.amt.dto.PlanInfoDTO;
import supt.amt.service.PlanInfoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public")
@Slf4j
public class PlanInfoController {
    private final PlanInfoService planInfoService;

    // retrieve
    @GetMapping("/plcys")
    public List<PlanInfoDTO> getPlanInfo() {
        return planInfoService.getPlanInfo();
    }
}
