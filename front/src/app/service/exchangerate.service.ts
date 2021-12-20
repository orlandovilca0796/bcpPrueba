import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Currency } from '../models/Currency';
import { ExchangeRate } from '../models/ExchangeRate';

@Injectable({
  providedIn: 'root'
})
export class ExchangerateService {

  exchangeRateURL = 'http://localhost:8081/exchangerate/';

  constructor(private httpClient: HttpClient) { }

  public lista1Currency(currency1Id:String): Observable<ExchangeRate[]> {
    return this.httpClient.get<ExchangeRate[]>(this.exchangeRateURL + 'id?currency1=' + currency1Id);
  }
  
  public lista2Currency(currency1Id:String, currency2Id:String): Observable<ExchangeRate[]> {
    return this.httpClient.get<ExchangeRate[]>(this.exchangeRateURL + 'id?currency1=' + currency1Id+'&currency2='+currency2Id);
  }

  public saveExchangeRate(exchangeRate:ExchangeRate): Observable<ExchangeRate> {
    return this.httpClient.post<any>(this.exchangeRateURL,exchangeRate);
  }

  public updateExchangeRate(exchangeRate:ExchangeRate): Observable<ExchangeRate> {
    return this.httpClient.put<any>(this.exchangeRateURL,exchangeRate);
  }
}
