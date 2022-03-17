import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacatureListComponent } from './vacature-list.component';

describe('VacatureListComponent', () => {
  let component: VacatureListComponent;
  let fixture: ComponentFixture<VacatureListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacatureListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
