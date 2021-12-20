import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroCurrencyComponent } from './registro-currency.component';

describe('RegistroCurrencyComponent', () => {
  let component: RegistroCurrencyComponent;
  let fixture: ComponentFixture<RegistroCurrencyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistroCurrencyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistroCurrencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
