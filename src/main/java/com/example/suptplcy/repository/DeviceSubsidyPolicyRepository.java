package com.example.suptplcy.repository;

import com.example.suptplcy.entity.DeviceSubsidyPolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceSubsidyPolicyRepository extends JpaRepository<DeviceSubsidyPolicyEntity, String> {
    public List<DeviceSubsidyPolicyEntity> findByDeviceCodeAndPlanCode(String deviceCode, String planCode);


    @Transactional
    @Modifying
    @Query("UPDATE ContractInfoEntity d " +
            "SET d.deviceCode = :deviceCode, d.deviceNumber = :deviceNumber,d.planCode = :planCode,d.contractDatetime = :contractDatetime " +
            "WHERE d.subscriptionId = :subscriptionId")
    void updateContractInfo(@Param("subscriptionId") String subscriptionId,
                            @Param("deviceCode") String deviceCode,
                            @Param("deviceNumber") String deviceNumber,
                            @Param("planCode") String planCode,
                            @Param("contractDatetime") LocalDateTime contractDatetime);
}
