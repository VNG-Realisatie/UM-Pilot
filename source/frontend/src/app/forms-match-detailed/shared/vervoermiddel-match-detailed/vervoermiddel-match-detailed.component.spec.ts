import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VervoermiddelMatchDetailedComponent} from './vervoermiddel-match-detailed.component';

describe('VervoermiddelMatchDetailedComponent', () => {
  let component: VervoermiddelMatchDetailedComponent;
  let fixture: ComponentFixture<VervoermiddelMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VervoermiddelMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VervoermiddelMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
