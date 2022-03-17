import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BemiddelingsberoepWerkzoekendeFormComponent} from './bemiddelingsberoep-werkzoekende-form.component';

describe('BemiddelingsberoepWerkzoekendeFormComponent', () => {
  let component: BemiddelingsberoepWerkzoekendeFormComponent;
  let fixture: ComponentFixture<BemiddelingsberoepWerkzoekendeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BemiddelingsberoepWerkzoekendeFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BemiddelingsberoepWerkzoekendeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
