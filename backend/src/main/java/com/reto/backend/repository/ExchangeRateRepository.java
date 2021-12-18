package com.reto.backend.repository;

import com.reto.backend.entity.Currency;
import com.reto.backend.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,String> {
    @Query(value = "SELECT er FROM exchange_rate er WHERE er.currency_origin_id IN (:lstCurrencyOriginId) ", nativeQuery = true)
    List<ExchangeRate> findyByListCurrencyOriginId(List<String> lstCurrencyOriginId);

    @Query(value = "SELECT er FROM exchange_rate er WHERE er.currency_destination_id IN (:lstCurrencyDestinationId) ", nativeQuery = true)
    List<ExchangeRate> findyByListCurrencyDestinationId(List<String> lstCurrencyDestinationId);

    @Query(value = "SELECT er FROM exchange_rate er WHERE er.currency_origin_id IN (:lstCurrencyOriginId) AND er.currency_destination_id IN (:lstCurrencyDestinationId) ", nativeQuery = true)
    List<ExchangeRate> findyByListCurrencyOriginDestinationId(List<String> lstCurrencyOriginId, List<String> lstCurrencyDestinationId);

}
