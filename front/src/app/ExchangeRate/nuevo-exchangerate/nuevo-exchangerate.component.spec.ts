import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoExchangerateComponent } from './nuevo-exchangerate.component';

describe('NuevoExchangerateComponent', () => {
  let component: NuevoExchangerateComponent;
  let fixture: ComponentFixture<NuevoExchangerateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevoExchangerateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoExchangerateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
