import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacatureImportComponent } from './vacature-import.component';

describe('VacatureImportComponent', () => {
  let component: VacatureImportComponent;
  let fixture: ComponentFixture<VacatureImportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacatureImportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
