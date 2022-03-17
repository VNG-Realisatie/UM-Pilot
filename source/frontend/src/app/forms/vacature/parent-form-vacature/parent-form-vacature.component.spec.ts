import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ParentFormVacatureComponent} from './parent-form-vacature.component';

describe('ParentFormComponent', () => {
  let component: ParentFormVacatureComponent;
  let fixture: ComponentFixture<ParentFormVacatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParentFormVacatureComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentFormVacatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
