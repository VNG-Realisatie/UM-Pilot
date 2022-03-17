import {TestBed} from '@angular/core/testing';

import {OinService} from './oin.service';

describe('OinService', () => {
  let service: OinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
