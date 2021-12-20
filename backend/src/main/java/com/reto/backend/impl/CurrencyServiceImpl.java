package com.reto.backend.impl;

import com.reto.backend.entity.Currency;
import com.reto.backend.repository.CurrencyRepository;
import com.reto.backend.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public Currency findCurrencyById(String currencyId) {
        return currencyRepository.findById(currencyId).orElse(null);
    }

    @Override
    public List<Currency> findCurrencyByIdPart(String currencyId){
        return currencyRepository.findByCurrencyIdContainingIgnoreCase(currencyId);
    }

    @Override
    public Currency saveCurrency(Currency currency){
        currency.setCreationDate(new Date());
        return currencyRepository.save(currency);
    }
}
