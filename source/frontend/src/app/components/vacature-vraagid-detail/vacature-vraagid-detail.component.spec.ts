import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VacatureVraagidDetailComponent} from './vacature-vraagid-detail.component';

describe('VacatureVraagidDetailComponent', () => {
  let component: VacatureVraagidDetailComponent;
  let fixture: ComponentFixture<VacatureVraagidDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VacatureVraagidDetailComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureVraagidDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
