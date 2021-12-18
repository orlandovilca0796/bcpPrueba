package com.reto.backend.repository;

import com.reto.backend.entity.AuditExchangeRate;
import com.reto.backend.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditExchangeRateRepository extends JpaRepository<AuditExchangeRate,String> {
    List<AuditExchangeRate> findByExchangeRate(ExchangeRate exchangeRate);
}
