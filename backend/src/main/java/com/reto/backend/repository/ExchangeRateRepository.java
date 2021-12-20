package com.reto.backend.repository;

import com.reto.backend.entity.Currency;
import com.reto.backend.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,String> {
    @Query(value = "SELECT er.* FROM exchange_rate er WHERE er.currency_origin_id = :currencyId OR er.currency_destination_id = :currencyId ", nativeQuery = true)
    List<ExchangeRate> findyByOneCurrency(String currencyId);

    @Query(value = "SELECT er.* FROM exchange_rate er " +
            " WHERE (er.currency_origin_id = :currency1Id AND er.currency_destination_id = :currency2Id) OR " +
            " (er.currency_origin_id = :currency2Id AND er.currency_destination_id = :currency1Id) ", nativeQuery = true)
    List<ExchangeRate> findyByTwoCurrency(String currency1Id, String currency2Id);

    @Query(value = "SELECT er.* FROM exchange_rate er WHERE er.currency_origin_id IN (:lstCurrencyOriginId) AND er.currency_destination_id IN (:lstCurrencyDestinationId) ", nativeQuery = true)
    List<ExchangeRate> findyByListCurrencyOriginDestinationId(List<String> lstCurrencyOriginId, List<String> lstCurrencyDestinationId);

    boolean existsById(String exchangeRateId);

}
