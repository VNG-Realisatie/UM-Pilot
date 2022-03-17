import { TestBed } from '@angular/core/testing';

import { VacatureBronService } from './vacature-bron.service';

describe('VacatureBronService', () => {
  let service: VacatureBronService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacatureBronService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
