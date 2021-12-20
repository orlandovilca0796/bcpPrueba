package com.reto.backend.rest;

import com.reto.backend.entity.Currency;
import com.reto.backend.entity.User;
import com.reto.backend.service.CurrencyService;
import com.reto.backend.util.Constants;
import com.reto.backend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Currency>> findyByName(@RequestParam(value = "id") String currencyId){
        List<Currency> lstCurrency = this.currencyService.findCurrencyByIdPart(currencyId);
        if(lstCurrency==null || lstCurrency.size()==0){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(lstCurrency);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Currency> saveCurrency(@Valid @RequestBody Currency currency, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Util.formatMessage(bindingResult));
        }
        Currency currencyBD = this.currencyService.findCurrencyById(currency.getCurrencyId());
        if(currencyBD != null ){
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.ERROR_NO_INSERT_CURRENCY.getValue()+currency.getCurrencyId());
        }
        currency.setCreationUser(User.builder().userId("PE123").build());
        Currency currency1 = this.currencyService.saveCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(currency1);
    }
}
