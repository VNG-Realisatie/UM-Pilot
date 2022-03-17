import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ParentFormWerkzoekendeComponent} from './parent-form-werkzoekende.component';

describe('ParentFormWerkzoekendeComponent', () => {
  let component: ParentFormWerkzoekendeComponent;
  let fixture: ComponentFixture<ParentFormWerkzoekendeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ParentFormWerkzoekendeComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentFormWerkzoekendeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
