package com.example.sales.repository;

import com.example.sales.entity.SaleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleInfoRepository extends JpaRepository<SaleInfoEntity, String> {
    // 추가적인 메서드가 필요한 경우 여기에 선언합니다.
}