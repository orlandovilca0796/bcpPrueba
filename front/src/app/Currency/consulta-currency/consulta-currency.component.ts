import { Component, OnInit } from '@angular/core';
import { Currency } from 'src/app/models/Currency';
import { CurrencyService } from 'src/app/service/currency.service';

@Component({
  selector: 'app-consulta-currency',
  templateUrl: './consulta-currency.component.html',
  styleUrls: ['./consulta-currency.component.css']
})
export class ConsultaCurrencyComponent implements OnInit {

  currencySelected:string;
  currencyFounded:Currency;
  lstCurrencies:Currency[];
  isFounded=false;

  constructor(private currencyService:CurrencyService) { }

  ngOnInit(): void {
    this.currencyService.getCurrencyAll().subscribe(
      data => {
        this.lstCurrencies = data;
      },
      err => {
      }
    );
  }

  onChangeCurrency():void{
    if(this.currencySelected!="NN"){
      this.currencyService.getById(this.currencySelected).subscribe(
        data => {
          this.isFounded=true;
          this.currencyFounded=data;
        },
        err => {

        }
      )
    }
  }

}
