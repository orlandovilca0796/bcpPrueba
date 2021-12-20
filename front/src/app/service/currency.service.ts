import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Currency } from '../models/Currency';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  authURL = 'http://localhost:8081/currency/';

  constructor(private httpClient: HttpClient) { }

  public getCurrencyAll(): Observable<Currency[]> {
    return this.httpClient.get<Currency[]>(this.authURL + 'all');
  } 

  public getById(currencyId:string): Observable<Currency> {
    return this.httpClient.get<Currency>(this.authURL + currencyId);
  } 

  public save(currency: Currency): Observable<Currency> {
    return this.httpClient.post<any>(this.authURL,currency);
  } 

}
