import {TestBed} from '@angular/core/testing';

import {VacatureBemiddelaarService} from './vacature-bemiddelaar.service';

describe('VacatureService', () => {
  let service: VacatureBemiddelaarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacatureBemiddelaarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
