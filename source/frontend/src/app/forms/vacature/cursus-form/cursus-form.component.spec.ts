import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CursusFormComponent} from './cursus-form.component';

describe('CursusFormComponent', () => {
  let component: CursusFormComponent;
  let fixture: ComponentFixture<CursusFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CursusFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CursusFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
