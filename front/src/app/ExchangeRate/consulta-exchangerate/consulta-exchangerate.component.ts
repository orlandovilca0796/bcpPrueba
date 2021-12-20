import { Component, OnInit } from '@angular/core';
import { Currency } from 'src/app/models/Currency';
import { ExchangeRate } from 'src/app/models/ExchangeRate';
import { CurrencyService } from 'src/app/service/currency.service';
import { ExchangerateService } from 'src/app/service/exchangerate.service';

@Component({
  selector: 'app-consulta-exchangerate',
  templateUrl: './consulta-exchangerate.component.html',
  styleUrls: ['./consulta-exchangerate.component.css']
})
export class ConsultaExchangerateComponent implements OnInit {

  lstCurrencyOrigen:Currency[]=[];
  lstCurrencyDestination:Currency[]=[];
  currencyOrigSelected:string="NN";
  currencyDestSelected:string="NN";
  lstExchangeRate:ExchangeRate[]=[];

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

  onChangeCurrency():void{
    if((this.currencyOrigSelected!="NN" && this.currencyDestSelected=="NN")||
      (this.currencyOrigSelected=="NN" && this.currencyDestSelected!="NN")){
      var currencySelected;
      if(this.currencyOrigSelected!="NN"){
        currencySelected = this.currencyOrigSelected;
      }else{
        currencySelected = this.currencyDestSelected;
      }
      this.exchangeRateService.lista1Currency(currencySelected).subscribe(
        data => {
          this.lstExchangeRate=data;
        }
      );
    }else {
      console.log("entra");
      this.exchangeRateService.lista2Currency(this.currencyOrigSelected,this.currencyDestSelected).subscribe(
        data => {
          this.lstExchangeRate=data;
        }
      );
    }
  }

  oncalculateTotal(exchangeRate:ExchangeRate){
    console.log((Number)((<HTMLInputElement>document.getElementById('monto'+exchangeRate.exchangeRateId)).value).toFixed(2));
    console.log(exchangeRate.exchangeRateBuyAmount);
    (<HTMLInputElement>document.getElementById('total'+exchangeRate.exchangeRateId)).innerHTML = ((Number)((<HTMLInputElement>document.getElementById('monto'+exchangeRate.exchangeRateId)).value)*exchangeRate.exchangeRateBuyAmount).toFixed(2);
    
  }

}
