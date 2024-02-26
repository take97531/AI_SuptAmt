package supt.amt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supt.amt.entity.PlanInfoEntity;

import java.util.List;

@Repository
public interface PlanInfoRepositoryImpl extends JpaRepository<PlanInfoEntity, String> {
    List<PlanInfoEntity> findAll();
}
