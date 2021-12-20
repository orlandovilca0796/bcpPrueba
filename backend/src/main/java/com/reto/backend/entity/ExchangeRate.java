package com.reto.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "exchange_rate")
@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class ExchangeRate {

    @Id
    @Column(name = "exchange_rate_id")
    private String exchangeRateId;

    @NotNull(message = "La moneda origen no puede ser vacia")
    @JoinColumn(name = "currency_origin_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Currency currencyOrigin;

    @NotNull(message = "La moneda destino no puede ser vacia")
    @JoinColumn(name = "currency_destination_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Currency currencyDestination;

    @Column(name = "exchange_rate_buy_amount")
    @Positive(message = "EL monto compra debe ser mayor a cero")
    private double exchangeRateBuyAmount;

}
