import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SectorMatchComponent} from './sector-match.component';

describe('SectorMatchComponent', () => {
  let component: SectorMatchComponent;
  let fixture: ComponentFixture<SectorMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SectorMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SectorMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
