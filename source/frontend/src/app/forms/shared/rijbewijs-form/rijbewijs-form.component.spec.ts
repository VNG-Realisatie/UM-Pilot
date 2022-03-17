import {ComponentFixture, TestBed} from '@angular/core/testing';

import {RijbewijsFormComponent} from './rijbewijs-form.component';

describe('RijbewijsFormComponent', () => {
  let component: RijbewijsFormComponent;
  let fixture: ComponentFixture<RijbewijsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RijbewijsFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RijbewijsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
