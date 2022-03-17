import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingWerkzoekendeFormComponent} from './opleiding-werkzoekende-form.component';

describe('OpleidingWerkzoekendeFormComponent', () => {
  let component: OpleidingWerkzoekendeFormComponent;
  let fixture: ComponentFixture<OpleidingWerkzoekendeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingWerkzoekendeFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingWerkzoekendeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
