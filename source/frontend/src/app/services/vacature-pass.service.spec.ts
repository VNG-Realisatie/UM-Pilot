import {TestBed} from '@angular/core/testing';

import {VacaturePassService} from './vacature-pass.service';

describe('VacaturePassService', () => {
  let service: VacaturePassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacaturePassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
