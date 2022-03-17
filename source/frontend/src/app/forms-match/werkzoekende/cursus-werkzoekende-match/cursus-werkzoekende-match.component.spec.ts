import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CursusWerkzoekendeMatchComponent} from './cursus-werkzoekende-match.component';

describe('CursusWerkzoekendeMatchComponent', () => {
  let component: CursusWerkzoekendeMatchComponent;
  let fixture: ComponentFixture<CursusWerkzoekendeMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CursusWerkzoekendeMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CursusWerkzoekendeMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
