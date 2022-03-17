import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkervaringWerkzoekendeFormComponent} from './werkervaring-werkzoekende-form.component';

describe('WerkervaringWerkzoekendeFormComponent', () => {
  let component: WerkervaringWerkzoekendeFormComponent;
  let fixture: ComponentFixture<WerkervaringWerkzoekendeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkervaringWerkzoekendeFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkervaringWerkzoekendeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
