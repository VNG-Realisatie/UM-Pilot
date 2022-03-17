import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContractvormFormComponent} from './contractvorm-form.component';

describe('ContractvormFormComponent', () => {
  let component: ContractvormFormComponent;
  let fixture: ComponentFixture<ContractvormFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ContractvormFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContractvormFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
