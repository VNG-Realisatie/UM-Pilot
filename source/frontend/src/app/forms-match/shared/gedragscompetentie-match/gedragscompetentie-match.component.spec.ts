import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GedragscompetentieMatchComponent} from './gedragscompetentie-match.component';

describe('GedragscompetentieMatchComponent', () => {
  let component: GedragscompetentieMatchComponent;
  let fixture: ComponentFixture<GedragscompetentieMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GedragscompetentieMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GedragscompetentieMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
