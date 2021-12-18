package com.reto.backend.rest;

import com.reto.backend.entity.AuditExchangeRate;
import com.reto.backend.entity.Currency;
import com.reto.backend.entity.ExchangeRate;
import com.reto.backend.entity.User;
import com.reto.backend.service.AuditExchangeRateService;
import com.reto.backend.service.CurrencyService;
import com.reto.backend.service.ExchangeRateService;
import com.reto.backend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            String error = this.formatMessage(result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
        }
        ExchangeRate exchangeRateSave = this.exchangeRateService.saveExchangeRate(exchangeRate);
        AuditExchangeRate auditExchangeRate = AuditExchangeRate.builder().
                                                auditUser(User.builder().userId("1").build()).
                                                reasonDescription(Constants.REASON_CREATION.getValue()).build();
        AuditExchangeRate auditExchangeRate1 = this.auditExchangeRateService.saveAudit(auditExchangeRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeRateSave);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable("id") String currencyId, @Valid @RequestBody ExchangeRate exchangeRate, BindingResult result){
        if(result.hasErrors()){
            String error = this.formatMessage(result);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, error);
        }
        exchangeRate.setExchangeRateId(currencyId);
        ExchangeRate exchangeRate1 = this.exchangeRateService.updateExchangeRate(exchangeRate);
        if(exchangeRate1==null){
            return ResponseEntity.notFound().build();
        }else{
            ExchangeRate exchangeRateSave = this.exchangeRateService.saveExchangeRate(exchangeRate);
            AuditExchangeRate auditExchangeRate = AuditExchangeRate.builder().
                    auditUser(User.builder().userId("1").build()).
                    reasonDescription(Constants.REASON_UPDATE.getValue()).build();
            AuditExchangeRate auditExchangeRate1 = this.auditExchangeRateService.saveAudit(auditExchangeRate);
            return ResponseEntity.ok(exchangeRate1);
        }
    }


    @GetMapping(value = "/id")
    public ResponseEntity<ExchangeRate> findByOriginDestinationId(@RequestParam(value = "originId", required = false) String currencyOriginId,
                                                                        @RequestParam(value = "destinationId", required = false) String currencyDestinationId){
        List<ExchangeRate> lstExchangeRate = this.exchangeRateService.findByOrigDestIdCurrency(currencyOriginId,currencyDestinationId);
        if(lstExchangeRate == null){
            return ResponseEntity.noContent().build();
        }else if(lstExchangeRate.size() != 1){
            return ResponseEntity.internalServerError().build();
        }else {
            return ResponseEntity.ok(lstExchangeRate.get(0));
        }
    }

    @GetMapping(value = "/name")
    public ResponseEntity<List<ExchangeRate>> findByOriginDestinationNamePart(@RequestParam(value = "originName", required = false) String currencyOrigin,
                                                                              @RequestParam(value = "destinationName", required = false) String currencyDestination){
        List<ExchangeRate> lstExchangeRate = null;
        if(currencyOrigin == null && currencyDestination == null){
            lstExchangeRate = this.exchangeRateService.findAll();
        }else if(currencyOrigin != null && currencyDestination == null){
            List<Currency> lstCurrencyOrigin = this.currencyService.findCurrencyByNamePart(currencyOrigin);
            if(lstCurrencyOrigin == null || lstCurrencyOrigin.size() == 0){
                return ResponseEntity.noContent().build();
            }else{
                lstExchangeRate = this.exchangeRateService.findByListOriginCurrency(lstCurrencyOrigin.stream().map(c->c.getCurrencyId()).collect(Collectors.toList()));
            }
        }else if(currencyOrigin == null && currencyDestination != null){
            List<Currency> lstCurrencyDestination = this.currencyService.findCurrencyByNamePart(currencyDestination);
            if(lstCurrencyDestination == null || lstCurrencyDestination.size() == 0){
                return ResponseEntity.noContent().build();
            }else{
                lstExchangeRate = this.exchangeRateService.findByListDestinationCurrency(lstCurrencyDestination.stream().map(c->c.getCurrencyId()).collect(Collectors.toList()));
            }
        }else {
            List<Currency> lstCurrencyOrigin = this.currencyService.findCurrencyByNamePart(currencyOrigin);
            List<Currency> lstCurrencyDestination = this.currencyService.findCurrencyByNamePart(currencyDestination);
            if((lstCurrencyOrigin == null || lstCurrencyOrigin.size() == 0) && (lstCurrencyDestination == null || lstCurrencyDestination.size() == 0)){
                return ResponseEntity.noContent().build();
            }else{
                lstExchangeRate = this.exchangeRateService.findByListOrigDestIdCurrency(lstCurrencyOrigin.stream().map(c->c.getCurrencyId()).collect(Collectors.toList()), lstCurrencyDestination.stream().map(c->c.getCurrencyId()).collect(Collectors.toList()));
            }
        }
        if(lstExchangeRate == null || lstExchangeRate.size() == 0){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(lstExchangeRate);
        }
    }

    private String formatMessage (BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        return errors.toString();
    }

}
