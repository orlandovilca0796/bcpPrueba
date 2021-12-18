package com.reto.backend.impl;

import com.reto.backend.entity.ExchangeRate;
import com.reto.backend.repository.ExchangeRateRepository;
import com.reto.backend.service.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> findAll() {
        return this.exchangeRateRepository.findAll();
    }

    @Override
    public List<ExchangeRate> findByListOriginCurrency(List<String> lstCurrencyOriginId) {
        return this.exchangeRateRepository.findyByListCurrencyOriginId(lstCurrencyOriginId);
    }

    @Override
    public List<ExchangeRate> findByListDestinationCurrency(List<String> lstCurrencyDestinationId) {
        return this.exchangeRateRepository.findyByListCurrencyDestinationId(lstCurrencyDestinationId);
    }

    @Override
    public List<ExchangeRate> findByListOrigDestIdCurrency(List<String> lstCurrencyOriginId, List<String> lstCurrencyDestinationId){
        return this.exchangeRateRepository.findyByListCurrencyOriginDestinationId(lstCurrencyOriginId, lstCurrencyDestinationId);
    }

    @Override
    public List<ExchangeRate> findByOrigDestIdCurrency(String currencyOriginId, String currencyDestinationId){
        return this.exchangeRateRepository.findyByListCurrencyOriginDestinationId(List.of(currencyOriginId),List.of(currencyDestinationId));
    }

    @Override
    public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate){
        exchangeRate.setExchangeRateId(UUID.randomUUID().toString());
        return this.exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public ExchangeRate updateExchangeRate(ExchangeRate exchangeRate){
        ExchangeRate exchangeRate1 = this.exchangeRateRepository.getById(exchangeRate.getExchangeRateId());
        if(exchangeRate1 != null){
            exchangeRate1 = this.exchangeRateRepository.save(exchangeRate);
        }else{
            exchangeRate1 = null;
        }
        return exchangeRate1;
    }
}
