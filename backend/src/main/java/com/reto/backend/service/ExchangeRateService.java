package com.reto.backend.service;

import com.reto.backend.entity.Currency;
import com.reto.backend.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> findAll();
    List<ExchangeRate> findByListOriginCurrency(List<String> lstCurrencyOriginId);
    List<ExchangeRate> findByListDestinationCurrency(List<String> lstCurrencyDestinationId);
    List<ExchangeRate> findByListOrigDestIdCurrency(List<String> lstCurrencyOriginId, List<String> lstCurrencyDestinationId);
    List<ExchangeRate> findByOrigDestIdCurrency(String currencyOriginId, String currencyDestinationId);
    ExchangeRate saveExchangeRate(ExchangeRate exchangeRate);
    ExchangeRate updateExchangeRate(ExchangeRate exchangeRate);
}
