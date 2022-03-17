import {ComponentFixture, TestBed} from '@angular/core/testing';

import {StraatadresBuitenlandMatchDetailedComponent} from './straatadres-buitenland-match-detailed.component';

describe('StraatadresBuitenlandMatchDetailedComponent', () => {
  let component: StraatadresBuitenlandMatchDetailedComponent;
  let fixture: ComponentFixture<StraatadresBuitenlandMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StraatadresBuitenlandMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StraatadresBuitenlandMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
