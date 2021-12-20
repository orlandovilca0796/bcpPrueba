package com.reto.backend.service;

import com.reto.backend.entity.Currency;
import com.reto.backend.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> findAll();
    ExchangeRate findById(String exchangeRateId);
    List<ExchangeRate> findByOneCurrency(String currencyId);
    List<ExchangeRate> findByTwoCurrency(String currency1Id, String currency2Id);
    List<ExchangeRate> findByListOrigDestIdCurrency(List<String> lstCurrencyOriginId, List<String> lstCurrencyDestinationId);
    List<ExchangeRate> findByOrigDestIdCurrency(String currencyOriginId, String currencyDestinationId);
    ExchangeRate saveExchangeRate(ExchangeRate exchangeRate);
    ExchangeRate updateExchangeRate(ExchangeRate exchangeRate);
}
