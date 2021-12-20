import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtDTO } from '../models/JwtDTO';
import { LoginAccount } from '../models/LoginAccount';
import { NewAccount } from '../models/NewAccount';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authURL = 'http://localhost:8081/auth/';

  constructor(private httpClient: HttpClient) { }

  public login(LoginAccount: LoginAccount): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', LoginAccount);
  } 

  public create(newAccount: NewAccount): Observable<any> {
    return this.httpClient.post<any>(this.authURL+'new', newAccount);
  }
}
