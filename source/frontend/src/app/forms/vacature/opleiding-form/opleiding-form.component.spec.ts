import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingFormComponent} from './opleiding-form.component';

describe('OpleidingFormComponent', () => {
  let component: OpleidingFormComponent;
  let fixture: ComponentFixture<OpleidingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
