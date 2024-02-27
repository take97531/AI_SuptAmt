package com.example.suptplcy.repository;

import com.example.suptplcy.entity.PlanInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanInfoRepository extends JpaRepository<PlanInfoEntity, String> {
    List<PlanInfoEntity> findAll();
}
