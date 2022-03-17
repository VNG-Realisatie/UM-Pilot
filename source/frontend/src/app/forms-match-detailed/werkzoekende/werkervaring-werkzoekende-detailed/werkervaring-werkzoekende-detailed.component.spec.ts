import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkervaringWerkzoekendeDetailedComponent} from './werkervaring-werkzoekende-detailed.component';

describe('WerkervaringWerkzoekendeDetailedComponent', () => {
  let component: WerkervaringWerkzoekendeDetailedComponent;
  let fixture: ComponentFixture<WerkervaringWerkzoekendeDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkervaringWerkzoekendeDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkervaringWerkzoekendeDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
