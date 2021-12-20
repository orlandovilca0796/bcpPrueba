package com.reto.backend.impl;

import com.reto.backend.entity.ExchangeRate;
import com.reto.backend.repository.ExchangeRateRepository;
import com.reto.backend.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> findAll() {
        return this.exchangeRateRepository.findAll();
    }

    @Override
    public ExchangeRate findById(String exchangeRateId){
        return this.exchangeRateRepository.findById(exchangeRateId).orElse(null);
    }

    @Override
    public List<ExchangeRate> findByOneCurrency(String currencyId) {
        return this.exchangeRateRepository.findyByOneCurrency(currencyId);
    }

    @Override
    public List<ExchangeRate> findByTwoCurrency(String currency1Id, String currency2Id) {
        return this.exchangeRateRepository.findyByTwoCurrency(currency1Id, currency2Id);
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
        boolean existeExchangeRate = this.exchangeRateRepository.existsById(exchangeRate.getExchangeRateId());
        if(existeExchangeRate){
            return this.exchangeRateRepository.save(exchangeRate);
        }else{
            return null;
        }
    }
}
