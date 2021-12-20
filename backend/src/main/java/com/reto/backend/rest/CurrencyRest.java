package com.reto.backend.rest;

import com.reto.backend.entity.Currency;
import com.reto.backend.security.entity.Account;
import com.reto.backend.security.entity.AccountMain;
import com.reto.backend.security.service.AccountService;
import com.reto.backend.service.CurrencyService;
import com.reto.backend.util.Constants;
import com.reto.backend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/currency")
@CrossOrigin
public class CurrencyRest {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Currency> findById(@PathVariable("id") String currencyId){
        Currency currency = this.currencyService.findCurrencyById(currencyId);
        if(currency==null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(currency);
        }
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Currency>> findAll(){
        List<Currency> lstCurrency = this.currencyService.findAll();
        if(lstCurrency==null && lstCurrency.size()==0){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(lstCurrency);
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
        AccountMain accountLogged = (AccountMain) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = this.accountService.findyByUsername(accountLogged.getUsername()).get();
        currency.setCreationUser(Account.builder().accountId(account.getAccountId()).build());
        Currency currency1 = this.currencyService.saveCurrency(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(currency1);
    }
}
