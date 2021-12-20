import { Component, OnInit } from '@angular/core';
import { Currency } from 'src/app/models/Currency';
import { ExchangeRate } from 'src/app/models/ExchangeRate';
import { CurrencyService } from 'src/app/service/currency.service';
import { ExchangerateService } from 'src/app/service/exchangerate.service';

@Component({
  selector: 'app-nuevo-exchangerate',
  templateUrl: './nuevo-exchangerate.component.html',
  styleUrls: ['./nuevo-exchangerate.component.css']
})
export class NuevoExchangerateComponent implements OnInit {

  currencyOrigSelected:string="NN";
  currencyDestSelected:string="NN";
  lstCurrencyOrigen:Currency[]=[];
  lstCurrencyDestination:Currency[]=[];
  isMessaje = false;
  errMsj: string;


  constructor(private currencyService:CurrencyService, private exchangeRateService:ExchangerateService) { }

  ngOnInit(): void {
    this.currencyService.getCurrencyAll().subscribe(
      data => {
        this.lstCurrencyOrigen = data;
        this.lstCurrencyDestination = data;
      },
      err => {
      }
    );
  }

  saveExchangeRate():void{
    console.log((<HTMLInputElement>document.getElementById('montoExchangeRate')).valueAsNumber);
    const exchangeRate = new ExchangeRate(new Currency(this.currencyOrigSelected,''),new Currency(this.currencyDestSelected,''), (<HTMLInputElement>document.getElementById('montoExchangeRate')).valueAsNumber);
    this.exchangeRateService.saveExchangeRate(exchangeRate).subscribe(
      data => {
        this.errMsj = "se creo el tipo de cambio";
        this.isMessaje = true;
      },
      err => {
        console.log("entra");
        this.isMessaje = true;
        this.errMsj = "ocurrio un error";
      }
    );
  }

}
