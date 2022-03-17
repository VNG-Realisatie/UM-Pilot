import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VacatureScreenComponent} from './vacature-screen.component';

describe('VacatureScreenComponent', () => {
  let component: VacatureScreenComponent;
  let fixture: ComponentFixture<VacatureScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VacatureScreenComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
