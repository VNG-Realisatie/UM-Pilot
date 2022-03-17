import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VervoermiddelFormComponent} from './vervoermiddel-form.component';

describe('VervoermiddelFormComponent', () => {
  let component: VervoermiddelFormComponent;
  let fixture: ComponentFixture<VervoermiddelFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VervoermiddelFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VervoermiddelFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
