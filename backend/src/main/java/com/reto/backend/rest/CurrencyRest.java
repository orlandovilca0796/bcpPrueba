package com.reto.backend.rest;

import com.reto.backend.entity.Currency;
import com.reto.backend.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyRest {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Currency> findyById(@PathVariable("id") String currencyId){
        Currency currency = this.currencyService.findCurrencyById(currencyId);
        if(currency==null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(currency);
        }
    }
}
