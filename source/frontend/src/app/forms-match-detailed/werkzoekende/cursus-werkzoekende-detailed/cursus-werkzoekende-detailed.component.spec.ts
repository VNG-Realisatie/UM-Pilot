import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CursusWerkzoekendeDetailedComponent} from './cursus-werkzoekende-detailed.component';

describe('CursusWerkzoekendeDetailedComponent', () => {
  let component: CursusWerkzoekendeDetailedComponent;
  let fixture: ComponentFixture<CursusWerkzoekendeDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CursusWerkzoekendeDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CursusWerkzoekendeDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
