import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkervaringWerkzoekendeMatchComponent} from './werkervaring-werkzoekende-match.component';

describe('WerkervaringWerkzoekendeMatchComponent', () => {
  let component: WerkervaringWerkzoekendeMatchComponent;
  let fixture: ComponentFixture<WerkervaringWerkzoekendeMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkervaringWerkzoekendeMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkervaringWerkzoekendeMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
