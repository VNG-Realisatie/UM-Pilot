import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VacatureVraagidListComponent} from './vacature-vraagid-list.component';

describe('VacatureVraagidListComponent', () => {
  let component: VacatureVraagidListComponent;
  let fixture: ComponentFixture<VacatureVraagidListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VacatureVraagidListComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacatureVraagidListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
