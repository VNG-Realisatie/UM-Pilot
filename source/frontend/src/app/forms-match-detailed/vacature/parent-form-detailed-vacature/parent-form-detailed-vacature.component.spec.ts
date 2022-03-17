import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ParentFormDetailedVacatureComponent} from './parent-form-detailed-vacature.component';

describe('ParentFormDetailedVacatureComponent', () => {
  let component: ParentFormDetailedVacatureComponent;
  let fixture: ComponentFixture<ParentFormDetailedVacatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParentFormDetailedVacatureComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentFormDetailedVacatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
