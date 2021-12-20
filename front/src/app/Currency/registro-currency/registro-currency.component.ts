import { Component, OnInit } from '@angular/core';
import { Currency } from 'src/app/models/Currency';
import { CurrencyService } from 'src/app/service/currency.service';

@Component({
  selector: 'app-registro-currency',
  templateUrl: './registro-currency.component.html',
  styleUrls: ['./registro-currency.component.css']
})
export class RegistroCurrencyComponent implements OnInit {

  isMessaje = false;
  errMsj: string;

  constructor(private currencyService:CurrencyService) { }

  ngOnInit(): void {
  }

  saveCurrency():void{
    const currency = new Currency((<HTMLInputElement>document.getElementById('currencyId')).value,(<HTMLInputElement>document.getElementById('currencyDesc')).value);
    this.currencyService.save(currency).subscribe(
      data => {
        this.errMsj = "se registro la moneda";
        this.isMessaje = true;
      },
      err => {
        this.isMessaje = true;
        this.errMsj = "ocurrio un error";
      }
    );
  }

}
