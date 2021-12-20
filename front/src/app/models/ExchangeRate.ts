import { Currency } from "./Currency";

export class ExchangeRate{
    exchangeRateId:string;
    currencyOrigin:Currency;
    currencyDestination:Currency;
    exchangeRateBuyAmount:number;


    constructor(currencyOrigin:Currency,currencyDestination:Currency,buyAmount:number){
        this.currencyOrigin = currencyOrigin;
        this.currencyDestination = currencyDestination;
        this.exchangeRateBuyAmount = buyAmount;
    }
}