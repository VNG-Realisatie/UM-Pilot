import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RijbewijsMatchComponent} from './rijbewijs-match.component';

describe('RijbewijsMatchComponent', () => {
  let component: RijbewijsMatchComponent;
  let fixture: ComponentFixture<RijbewijsMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RijbewijsMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RijbewijsMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
