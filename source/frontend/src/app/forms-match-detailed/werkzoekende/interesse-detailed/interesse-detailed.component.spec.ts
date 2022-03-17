import {ComponentFixture, TestBed} from '@angular/core/testing';

import {InteresseDetailedComponent} from './interesse-detailed.component';

describe('InteresseDetailedComponent', () => {
  let component: InteresseDetailedComponent;
  let fixture: ComponentFixture<InteresseDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InteresseDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InteresseDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
