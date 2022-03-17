import {TestBed} from '@angular/core/testing';

import {WerkzoekendeBemiddelaarService} from './werkzoekende-bemiddelaar.service';

describe('WerkzoekendeBemiddelaarService', () => {
  let service: WerkzoekendeBemiddelaarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WerkzoekendeBemiddelaarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
