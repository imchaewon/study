package com.icw.common.entity.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUDIT")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "REQ_URI")
    private String reqUri;

    @Column(name = "REQ_METHOD")
    private String reqMethod;

    @Column(name = "CLIENT_IP")
    private String clientIp;

    @Column(name = "RES_STATUS_CODE")
    private int resStatusCode;

    @Column(name = "API_DESCRIPTION")
    private String apiDesc;

    @Column(name = "REQ_DATE")
    private LocalDateTime reqDate;

    @Column(name = "RES_DATE")
    private LocalDateTime resDate;

    // 필요시 사용할 양방향 맵핑 관계
    @OneToOne(mappedBy = "audit")
    private AuditError auditError;
}
