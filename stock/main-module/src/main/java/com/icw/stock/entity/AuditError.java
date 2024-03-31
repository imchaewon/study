package com.icw.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUDIT_ERROR")
public class AuditError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "ERROR_STATUS_CODE")
    private int errorStatusCode;

    @Column(name = "ERROR_MSG")
    private String errorMessage;

    @OneToOne
    @JoinColumn(name = "AUDIT_ID", unique = true)
    private Audit audit;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
}
