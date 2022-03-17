import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CursusWerkzoekendeFormComponent} from './cursus-werkzoekende-form.component';

describe('CursusWerkzoekendeFormComponent', () => {
  let component: CursusWerkzoekendeFormComponent;
  let fixture: ComponentFixture<CursusWerkzoekendeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CursusWerkzoekendeFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CursusWerkzoekendeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
