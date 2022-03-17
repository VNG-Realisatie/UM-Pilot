import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingWerkzoekendeMatchComponent} from './opleiding-werkzoekende-match.component';

describe('OpleidingWerkzoekendeMatchComponent', () => {
  let component: OpleidingWerkzoekendeMatchComponent;
  let fixture: ComponentFixture<OpleidingWerkzoekendeMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingWerkzoekendeMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingWerkzoekendeMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
