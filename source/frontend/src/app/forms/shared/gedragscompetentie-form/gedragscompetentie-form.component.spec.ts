import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GedragscompetentieFormComponent} from './gedragscompetentie-form.component';

describe('GedragscompetentieFormComponent', () => {
  let component: GedragscompetentieFormComponent;
  let fixture: ComponentFixture<GedragscompetentieFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GedragscompetentieFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GedragscompetentieFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
