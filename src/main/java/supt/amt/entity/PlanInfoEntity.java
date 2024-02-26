package supt.amt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN_INFO")
public class PlanInfoEntity {
    @Id
    @Column(name = "PLAN_CODE", nullable = false)
    private String planCode;

    @Column(name = "PLAN_NAME")
    private String planName;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATE_PROGRAM", nullable = false)
    private String createProgram;

    @Column(name = "CREATE_DATETIME", nullable = false)
    private LocalDateTime createDatetime;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATE_PROGRAM")
    private String updateProgram;

    @Column(name = "UPDATE_DATETIME")
    private LocalDateTime updateDatetime;
}
