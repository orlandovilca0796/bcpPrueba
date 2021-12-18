package com.reto.backend.service;


import com.reto.backend.entity.Currency;

import java.util.List;

public interface CurrencyService {
    Currency findCurrencyById(String currencyId);
    List<Currency> findCurrencyByNamePart(String currencyName);
}
