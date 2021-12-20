import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaExchangerateComponent } from './consulta-exchangerate.component';

describe('ConsultaExchangerateComponent', () => {
  let component: ConsultaExchangerateComponent;
  let fixture: ComponentFixture<ConsultaExchangerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultaExchangerateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaExchangerateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
