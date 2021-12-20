import { TestBed } from '@angular/core/testing';

import { ProdInterceptorsService } from './prod-interceptors.service';

describe('ProdInterceptorsService', () => {
  let service: ProdInterceptorsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProdInterceptorsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
