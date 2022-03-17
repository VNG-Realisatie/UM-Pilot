import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ParentFormDetailedWerkzoekendeComponent} from './parent-form-detailed-werkzoekende.component';

describe('ParentFormDetailedWerkzoekendeComponent', () => {
  let component: ParentFormDetailedWerkzoekendeComponent;
  let fixture: ComponentFixture<ParentFormDetailedWerkzoekendeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParentFormDetailedWerkzoekendeComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentFormDetailedWerkzoekendeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
