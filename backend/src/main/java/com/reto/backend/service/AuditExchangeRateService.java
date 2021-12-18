package com.reto.backend.service;

import com.reto.backend.entity.AuditExchangeRate;
import com.reto.backend.entity.ExchangeRate;

import java.util.List;

public interface AuditExchangeRateService {
    AuditExchangeRate saveAudit(AuditExchangeRate auditExchangeRate);
    List<AuditExchangeRate> findByExchangeRateId(ExchangeRate exchangeRate);
}
