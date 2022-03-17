import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VacatureRequestComponent} from './vacature-request.component';

describe('VacatureRequestComponent', () => {
  let component: VacatureRequestComponent;
  let fixture: ComponentFixture<VacatureRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VacatureRequestComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
