import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingWerkzoekendeDetailedComponent} from './opleiding-werkzoekende-detailed.component';

describe('OpleidingWerkzoekendeDetailedComponent', () => {
  let component: OpleidingWerkzoekendeDetailedComponent;
  let fixture: ComponentFixture<OpleidingWerkzoekendeDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingWerkzoekendeDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingWerkzoekendeDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
