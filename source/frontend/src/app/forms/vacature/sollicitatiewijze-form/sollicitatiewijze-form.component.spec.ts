import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SollicitatiewijzeFormComponent} from './sollicitatiewijze-form.component';

describe('SollicitatiewijzeFormComponent', () => {
  let component: SollicitatiewijzeFormComponent;
  let fixture: ComponentFixture<SollicitatiewijzeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SollicitatiewijzeFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SollicitatiewijzeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
