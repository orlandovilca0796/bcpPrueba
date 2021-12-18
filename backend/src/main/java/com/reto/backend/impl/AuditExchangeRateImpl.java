package com.reto.backend.impl;

import com.reto.backend.entity.AuditExchangeRate;
import com.reto.backend.entity.ExchangeRate;
import com.reto.backend.repository.AuditExchangeRateRepository;
import com.reto.backend.service.AuditExchangeRateService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AuditExchangeRateImpl implements AuditExchangeRateService {

    private AuditExchangeRateRepository auditExchangeRateRepository;

    @Override
    public AuditExchangeRate saveAudit(AuditExchangeRate auditExchangeRate) {
        auditExchangeRate.setAuditId(UUID.randomUUID().toString());
        auditExchangeRate.setAuditDate(new Date());
        return auditExchangeRateRepository.save(auditExchangeRate);
    }

    @Override
    public List<AuditExchangeRate> findByExchangeRateId(ExchangeRate exchangeRate){
        return auditExchangeRateRepository.findByExchangeRate(exchangeRate);
    }
}
