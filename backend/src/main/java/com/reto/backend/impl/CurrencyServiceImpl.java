package com.reto.backend.impl;

import com.reto.backend.entity.Currency;
import com.reto.backend.repository.CurrencyRepository;
import com.reto.backend.service.CurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Override
    public Currency findCurrencyById(String currencyId) {
        return currencyRepository.findById(currencyId).orElse(null);
    }

    @Override
    public List<Currency> findCurrencyByNamePart(String currencyName){
        return currencyRepository.findByCurrencyNameContainingIgnoreCase(currencyName);
    }
}
