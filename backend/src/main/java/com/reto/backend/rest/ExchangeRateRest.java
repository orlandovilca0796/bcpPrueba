package com.reto.backend.rest;

import com.reto.backend.entity.AuditExchangeRate;
import com.reto.backend.entity.Currency;
import com.reto.backend.entity.ExchangeRate;
import com.reto.backend.entity.User;
import com.reto.backend.service.AuditExchangeRateService;
import com.reto.backend.service.CurrencyService;
import com.reto.backend.service.ExchangeRateService;
import com.reto.backend.util.Constants;
import com.reto.backend.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/exchangerate")
public class ExchangeRateRest {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private AuditExchangeRateService auditExchangeRateService;

    @PostMapping
    public ResponseEntity<ExchangeRate> createExchangeRate(@Valid @RequestBody ExchangeRate exchangeRate, BindingResult result){
        if (result.hasErrors()){
            String error = Util.formatMessage(result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
        }
        List<ExchangeRate> lstVerify = this.exchangeRateService.findByTwoCurrency(exchangeRate.getCurrencyOrigin().getCurrencyId(), exchangeRate.getCurrencyDestination().getCurrencyId());
        if(lstVerify != null && lstVerify.size()>0){
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.ERROR_CREATE_EXCHANGE_RATE_DUPLICATE.getValue());
        }
        ExchangeRate exchangeRateSave = this.exchangeRateService.saveExchangeRate(exchangeRate);
        AuditExchangeRate auditExchangeRate = AuditExchangeRate.builder().
                                                exchangeRate(ExchangeRate.builder().exchangeRateId(exchangeRateSave.getExchangeRateId()).build()).
                                                auditUser(User.builder().userId("PE123").build()).
                                                reasonDescription(Constants.REASON_CREATION.getValue()).build();
        AuditExchangeRate auditExchangeRate1 = this.auditExchangeRateService.saveAudit(auditExchangeRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeRateSave);
    }

    @PutMapping
    public ResponseEntity<ExchangeRate> updateExchangeRate(@Valid @RequestBody ExchangeRate exchangeRate, BindingResult result){
        if(result.hasErrors() || exchangeRate.getExchangeRateId() == null){
            if(exchangeRate.getExchangeRateId() == null){
                ObjectError errorId = new ObjectError("exchange_rate_id",Constants.ERROR_UPDATE_EXCHANGE_RATE_NEED_ID.getValue());
                result.addError(errorId);
            }
            String error = Util.formatMessage(result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
        }
        ExchangeRate exchangeRateSave = this.exchangeRateService.updateExchangeRate(exchangeRate);
        if(exchangeRateSave == null){
            return ResponseEntity.notFound().build();
        }
        AuditExchangeRate auditExchangeRate = AuditExchangeRate.builder().
                exchangeRate(ExchangeRate.builder().exchangeRateId(exchangeRate.getExchangeRateId()).build()).
                auditUser(User.builder().userId("PE123").build()).
                reasonDescription(Constants.REASON_UPDATE.getValue()).build();
        AuditExchangeRate auditExchangeRate1 = this.auditExchangeRateService.saveAudit(auditExchangeRate);
        return ResponseEntity.ok(exchangeRateSave);
    }

    @GetMapping(value = "/id")
    public ResponseEntity<List<ExchangeRate>> findByOriginDestinationNamePart(@RequestParam(value = "currency1", required = false) String currency1,
                                                                              @RequestParam(value = "currency2", required = false) String currency2){
        List<ExchangeRate> lstExchangeRate = null;
        List<String> lstCurrencyId = new ArrayList<>();
        if(currency1 == null && currency2 == null){
            lstExchangeRate = this.exchangeRateService.findAll();
        }else {
            if(currency1 != null) {
                lstCurrencyId.add(currency1);
            }
            if(currency2 != null) {
                lstCurrencyId.add(currency2);
            }
            if(lstCurrencyId.size()==1) {
                lstExchangeRate = this.exchangeRateService.findByOneCurrency(lstCurrencyId.get(0));
            }else {
                lstExchangeRate = this.exchangeRateService.findByTwoCurrency(lstCurrencyId.get(0),lstCurrencyId.get(1));
            }
        }
        if(lstExchangeRate == null || lstExchangeRate.size() == 0){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(lstExchangeRate);
        }
    }
}
