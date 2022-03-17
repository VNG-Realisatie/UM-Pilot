import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VervoermiddelMatchComponent} from './vervoermiddel-match.component';

describe('VervoermiddelMatchComponent', () => {
  let component: VervoermiddelMatchComponent;
  let fixture: ComponentFixture<VervoermiddelMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VervoermiddelMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VervoermiddelMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
