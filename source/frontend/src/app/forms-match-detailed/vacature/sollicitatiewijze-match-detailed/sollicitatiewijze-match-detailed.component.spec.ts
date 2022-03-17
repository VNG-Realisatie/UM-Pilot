import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SollicitatiewijzeMatchDetailedComponent} from './sollicitatiewijze-match-detailed.component';

describe('SollicitatiewijzeMatchDetailedComponent', () => {
  let component: SollicitatiewijzeMatchDetailedComponent;
  let fixture: ComponentFixture<SollicitatiewijzeMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SollicitatiewijzeMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SollicitatiewijzeMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
