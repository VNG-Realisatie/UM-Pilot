import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkzoekendeVraagidListComponent} from './werkzoekende-vraagid-list.component';

describe('WerkzoekendeVraagidListComponent', () => {
  let component: WerkzoekendeVraagidListComponent;
  let fixture: ComponentFixture<WerkzoekendeVraagidListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkzoekendeVraagidListComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeVraagidListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
