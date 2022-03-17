import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FlexibiliteitFormComponent} from './flexibiliteit-form.component';

describe('FlexibiliteitFormComponent', () => {
  let component: FlexibiliteitFormComponent;
  let fixture: ComponentFixture<FlexibiliteitFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FlexibiliteitFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlexibiliteitFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
