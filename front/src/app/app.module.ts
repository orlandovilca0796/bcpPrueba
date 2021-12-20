import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConsultaExchangerateComponent } from './ExchangeRate/consulta-exchangerate/consulta-exchangerate.component';
import { NuevoExchangerateComponent } from './ExchangeRate/nuevo-exchangerate/nuevo-exchangerate.component';
import { LoginComponent } from './Account/login/login.component';
import { NuevaCuentaComponent } from './Account/nueva-cuenta/nueva-cuenta.component';
import { IndexComponent } from './Index/index/index.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenuComponent } from './menu/menu.component';
import { ConsultaCurrencyComponent } from './Currency/consulta-currency/consulta-currency.component';
import { RegistroCurrencyComponent } from './Currency/registro-currency/registro-currency.component';
import { interceptorProvider } from './interceptors/prod-interceptors.service';

@NgModule({
  declarations: [
    AppComponent,
    ConsultaExchangerateComponent,
    NuevoExchangerateComponent,
    LoginComponent,
    NuevaCuentaComponent,
    IndexComponent,
    MenuComponent,
    ConsultaCurrencyComponent,
    RegistroCurrencyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
