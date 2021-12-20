package com.reto.backend.rest;

import com.reto.backend.entity.AuditExchangeRate;
import com.reto.backend.entity.ExchangeRate;
import com.reto.backend.service.AuditExchangeRateService;
import com.reto.backend.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/audit")
@CrossOrigin
public class AuditExchangeRateRest {
    @Autowired
    private AuditExchangeRateService auditExchangeRateService;
    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public ResponseEntity<List<AuditExchangeRate>> findByOriginDestinationId(@RequestParam(name = "originId", required = true) String currencyOriginId,
                                                                             @RequestParam(name = "destinationId", required = true) String currencyDestinationId){
        List<ExchangeRate> lstExchangeRate = this.exchangeRateService.findByOrigDestIdCurrency(currencyOriginId,currencyDestinationId);
        if(lstExchangeRate == null){
            return ResponseEntity.notFound().build();
        }else if(lstExchangeRate.size() != 1){
            return ResponseEntity.internalServerError().build();
        }
        List<AuditExchangeRate> lstAuditExchangeRate = this.auditExchangeRateService.findByExchangeRateId(lstExchangeRate.get(0));
        if(lstAuditExchangeRate == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lstAuditExchangeRate);
    }
}
