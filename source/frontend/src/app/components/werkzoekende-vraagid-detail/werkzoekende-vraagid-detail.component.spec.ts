import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkzoekendeVraagidDetailComponent} from './werkzoekende-vraagid-detail.component';

describe('WerkzoekendeVraagidDetailComponent', () => {
  let component: WerkzoekendeVraagidDetailComponent;
  let fixture: ComponentFixture<WerkzoekendeVraagidDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkzoekendeVraagidDetailComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeVraagidDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
