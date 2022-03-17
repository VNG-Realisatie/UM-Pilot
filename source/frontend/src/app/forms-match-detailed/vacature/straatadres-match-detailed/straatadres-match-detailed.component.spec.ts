import {ComponentFixture, TestBed} from '@angular/core/testing';

import {StraatadresMatchDetailedComponent} from './straatadres-match-detailed.component';

describe('StraatadresMatchDetailedComponent', () => {
  let component: StraatadresMatchDetailedComponent;
  let fixture: ComponentFixture<StraatadresMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StraatadresMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StraatadresMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
