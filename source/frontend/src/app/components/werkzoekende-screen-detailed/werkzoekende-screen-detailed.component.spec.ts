import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkzoekendeScreenDetailedComponent} from './werkzoekende-screen-detailed.component';

describe('WerkzoekendeScreenDetailedComponent', () => {
  let component: WerkzoekendeScreenDetailedComponent;
  let fixture: ComponentFixture<WerkzoekendeScreenDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkzoekendeScreenDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkzoekendeScreenDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
