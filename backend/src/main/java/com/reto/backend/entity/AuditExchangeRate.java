package com.reto.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit_exchange_rate")
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class AuditExchangeRate {
    @Id
    @Column(name = "audit_log_id")
    private String auditId;

    @JoinColumn(name = "exchange_rate_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ExchangeRate exchangeRate;

    @Column(name = "reason_desc")
    private String reasonDescription;

    @JoinColumn(name = "audit_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private User auditUser;

    @Column(name = "audit_date")
    private Date auditDate;
}
