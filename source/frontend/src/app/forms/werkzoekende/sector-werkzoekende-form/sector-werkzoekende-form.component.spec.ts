import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SectorWerkzoekendeFormComponent} from './sector-werkzoekende-form.component';

describe('SectorWerkzoekendeFormComponent', () => {
  let component: SectorWerkzoekendeFormComponent;
  let fixture: ComponentFixture<SectorWerkzoekendeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SectorWerkzoekendeFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SectorWerkzoekendeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
