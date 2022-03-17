import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacatureViewComponent } from './vacature-view.component';

describe('VacatureViewComponent', () => {
  let component: VacatureViewComponent;
  let fixture: ComponentFixture<VacatureViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacatureViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
