import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FlexibiliteitMatchComponent} from './flexibiliteit-match.component';

describe('FlexibiliteitMatchComponent', () => {
  let component: FlexibiliteitMatchComponent;
  let fixture: ComponentFixture<FlexibiliteitMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FlexibiliteitMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlexibiliteitMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
