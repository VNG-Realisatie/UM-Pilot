import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MobiliteitWerkzoekendeMatchComponent} from './mobiliteit-werkzoekende-match.component';

describe('MobiliteitWerkzoekendeMatchComponent', () => {
  let component: MobiliteitWerkzoekendeMatchComponent;
  let fixture: ComponentFixture<MobiliteitWerkzoekendeMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MobiliteitWerkzoekendeMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MobiliteitWerkzoekendeMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
