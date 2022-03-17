import {TestBed} from '@angular/core/testing';

import {WerkzoekendePassService} from './werkzoekende-pass.service';

describe('WerkzoekendePassService', () => {
  let service: WerkzoekendePassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WerkzoekendePassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
