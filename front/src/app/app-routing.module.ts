import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Account/login/login.component';
import { NuevaCuentaComponent } from './Account/nueva-cuenta/nueva-cuenta.component';
import { ConsultaCurrencyComponent } from './Currency/consulta-currency/consulta-currency.component';
import { RegistroCurrencyComponent } from './Currency/registro-currency/registro-currency.component';
import { ConsultaExchangerateComponent } from './ExchangeRate/consulta-exchangerate/consulta-exchangerate.component';
import { NuevoExchangerateComponent } from './ExchangeRate/nuevo-exchangerate/nuevo-exchangerate.component';
import { IndexComponent } from './Index/index/index.component';
import { GuardService as guard } from './service/guard.service';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: NuevaCuentaComponent },
  { path: 'exchangerateconsulta', component: ConsultaExchangerateComponent },
  { path: 'exchangerateregistro', component: NuevoExchangerateComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'currencyregistro', component: RegistroCurrencyComponent, canActivate: [guard], data: { expectedRol: ['admin'] } },
  { path: 'currency', component: ConsultaCurrencyComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
