package com.reto.backend.repository;

import com.reto.backend.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
    List<Currency> findByCurrencyNameContainingIgnoreCase(String currencyName);
}
