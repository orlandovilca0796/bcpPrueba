import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaCurrencyComponent } from './consulta-currency.component';

describe('ConsultaCurrencyComponent', () => {
  let component: ConsultaCurrencyComponent;
  let fixture: ComponentFixture<ConsultaCurrencyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaCurrencyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaCurrencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
