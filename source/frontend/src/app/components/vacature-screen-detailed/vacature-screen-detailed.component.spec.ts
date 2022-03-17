import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VacatureScreenDetailedComponent} from './vacature-screen-detailed.component';

describe('VacatureScreenDetailedComponent', () => {
  let component: VacatureScreenDetailedComponent;
  let fixture: ComponentFixture<VacatureScreenDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VacatureScreenDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureScreenDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
