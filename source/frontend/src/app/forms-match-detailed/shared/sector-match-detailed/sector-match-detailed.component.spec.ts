import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SectorMatchDetailedComponent} from './sector-match-detailed.component';

describe('SectorWerkzoekendeMatchComponent', () => {
  let component: SectorMatchDetailedComponent;
  let fixture: ComponentFixture<SectorMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SectorMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SectorMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
